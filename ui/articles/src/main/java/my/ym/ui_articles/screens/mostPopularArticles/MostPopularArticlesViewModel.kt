package my.ym.ui_articles.screens.mostPopularArticles

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.application
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import my.ym.domain_articles.repos.RepoArticles
import my.ym.domain_shared.models.AppResult
import my.ym.ui_articles.R
import my.ym.ui_shared.utils.getLocalizedText
import java.time.Duration
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.hours
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toKotlinDuration

class MostPopularArticlesViewModel(
	application: Application,
	private val repoArticles: RepoArticles,
) : AndroidViewModel(application) {

	var state by mutableStateOf(MostPopularArticlesState())
		private set

	private var jobOfLoopOfTheUpdatedSinceText: Job? = null
	private var jobOfLoadDataAndObserveIt: Job? = null

	init {
		loadDataAndObserveIt()
	}

	fun handleIntent(intent: MostPopularArticlesIntent) {
		state = when (intent) {
			is MostPopularArticlesIntent.ChangeVisibilityOfInfoTopSheet -> state.copy(
				showInfoTopSheet = intent.show
			)
			MostPopularArticlesIntent.HideMessageAtBottom -> state.copy(
				previousMessageAtBottom = state.messageAtBottom,
				messageAtBottom = null,
			)
			MostPopularArticlesIntent.PullToRefresh -> {
				loadDataAndObserveIt()

				state.copy(
					loadingStatus = MostPopularArticlesState.LoadingStatus.PullingToRefresh,
				)
			}
			MostPopularArticlesIntent.RetryToFetchData -> {
				loadDataAndObserveIt()

				return
			}
		}
	}

	private fun loadDataAndObserveIt() {
		jobOfLoadDataAndObserveIt?.cancel()
		jobOfLoadDataAndObserveIt = viewModelScope.launch(context = Dispatchers.Default) {
			// todo repo should handle loading case isa.
			repoArticles.getMostPopularViewedArticles(periodInDays = 1).collectLatest { appResult ->
				val newState = when (appResult) {
					is AppResult.Failure -> {
						state.copy(
							loadingStatus = MostPopularArticlesState.LoadingStatus.None,
							messageAtBottom = null,
							previousMessageAtBottom = state.messageAtBottom,
							messageAtTop = null,
							data = MostPopularArticlesState.Data.Error(
								msg = appResult.reason.getLocalizedText(context = application)
							),
						)
					}
					is AppResult.Success -> {
						val (messageAtTop, delayDuration) = appResult.data
							.fetchedFromApiAt.getUpdatedSinceTextAndNextDelay()

						loopAndUpdateTheUpdatedSinceText(initialDelay = delayDuration)

						val messageAtBottom = when (state.messageAtBottom?.type) {
							MostPopularArticlesState.MessageType.Error,
							MostPopularArticlesState.MessageType.Warning -> {
								MostPopularArticlesState.MessageWithType(
									message = application.getString(R.string.data_fetched_from_internet_successfully_and_is_now_up_to_date),
									type = MostPopularArticlesState.MessageType.Success,
								)
							}
							else -> {
								null
							}
						}

						state.copy(
							loadingStatus = MostPopularArticlesState.LoadingStatus.None,
							data = MostPopularArticlesState.Data.Success(
								snapshotOfArticles = appResult.data,
							),
							messageAtTop = messageAtTop,
							previousMessageAtBottom = state.messageAtBottom,
							messageAtBottom = messageAtBottom,
						)
					}
					is AppResult.Loading -> {
						if (state.isLoading) state else {
							state.copy(
								loadingStatus = MostPopularArticlesState.LoadingStatus.Other,
								messageAtBottom = null,
								previousMessageAtBottom = state.messageAtBottom,
							)
						}
					}
				}

				withContext(context = Dispatchers.Main) {
					state = newState
				}
			}
		}
	}

	// todo see if can be done in repo msln inshallah.
	private fun loopAndUpdateTheUpdatedSinceText(initialDelay: kotlin.time.Duration) {
		jobOfLoopOfTheUpdatedSinceText?.cancel()
		jobOfLoopOfTheUpdatedSinceText = viewModelScope.launch(context = Dispatchers.Default) {
			var delayDuration = initialDelay
			while (true) {
				if (isActive.not()) {
					break
				}

				delay(duration = delayDuration)

				val fetchedFromApiAt = (state.data as? MostPopularArticlesState.Data.Success)
					?.snapshotOfArticles?.fetchedFromApiAt ?: break

				val (message, newDelayDuration) = fetchedFromApiAt
					.getUpdatedSinceTextAndNextDelay()
				withContext(context = Dispatchers.Main) {
					state = state.copy(messageAtTop = message)
				}

				delayDuration = newDelayDuration
			}
		}
	}

	private fun LocalDateTime.getUpdatedSinceTextAndNextDelay(): Pair<String, kotlin.time.Duration> {
		val javaDuration = Duration.between(this, LocalDateTime.now())
		val kotlinDuration = javaDuration.toKotlinDuration()

		return when {
			kotlinDuration < 5.seconds -> Pair(
				application.getString(R.string.just_now),
				5.seconds - kotlinDuration
			)
			kotlinDuration < 1.minutes -> Pair(
				application.getString(R.string.moments_ago),
				1.minutes - kotlinDuration
			)
			kotlinDuration < 1.hours -> Pair(
				"${kotlinDuration.inWholeMinutes} ${application.getString(R.string.minutes_ago)}",
				1.minutes
			)
			else -> Pair(
				"${kotlinDuration.inWholeHours} ${application.getString(R.string.hours_ago)}",
				1.hours
			)
		}.let {
			it.copy(first = "${application.getString(R.string.updated)} ${it.first}", second = it.second)
		}
	}

	override fun onCleared() {
		jobOfLoopOfTheUpdatedSinceText?.cancel()
		jobOfLoopOfTheUpdatedSinceText = null

		jobOfLoadDataAndObserveIt?.cancel()
		jobOfLoadDataAndObserveIt = null

		super.onCleared()
	}

}
