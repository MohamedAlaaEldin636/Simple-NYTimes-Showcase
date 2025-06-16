package my.ym.ui_articles.screens.articleDetails

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import my.ym.domain_articles.useCases.GetViewedArticleDetailsUseCase
import my.ym.domain_shared.models.AppResult
import my.ym.ui_articles.screens.articleDetails.utils.toListOfAppMediaMetadataWithType
import javax.inject.Inject
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class ArticleDetailsViewModel @Inject constructor(
	application: Application,
	savedStateHandle: SavedStateHandle,
	private val getViewedArticleDetailsUseCase: GetViewedArticleDetailsUseCase,
) : AndroidViewModel(application) {

	private val articleId = savedStateHandle.toRoute<ArticleDetailsDestination>().id

	var state by mutableStateOf(ArticleDetailsState())
		private set

	private var jobOfLoadDataAndObserveIt: Job? = null

	private var jobOfLoopOfAutoMoveToNextImage: Job? = null

	init {
		loadDataAndObserveIt()
	}

	fun handleIntent(intent: ArticleDetailsIntent) {
		state = when (intent) {
			is ArticleDetailsIntent.SelectImage -> {
				state.copy(
					selectedImageIndex = intent.index
				)
			}
			ArticleDetailsIntent.RetryToFetchData -> {
				loadDataAndObserveIt()

				return
			}
		}
	}

	private fun loadDataAndObserveIt() {
		jobOfLoadDataAndObserveIt?.cancel()
		jobOfLoadDataAndObserveIt = viewModelScope.launch {
			getViewedArticleDetailsUseCase(id = articleId).collectLatest { appResult ->
				state = state.copy(
					appResult = appResult,
					selectedImageIndex = 0,
				)

				jobOfLoopOfAutoMoveToNextImage?.cancel()

				if (appResult is AppResult.Success) {
					val sizeOfImages = appResult.data.toListOfAppMediaMetadataWithType().size
					if (sizeOfImages > 0) {
						jobOfLoopOfAutoMoveToNextImage = launch(Dispatchers.Default) {
							while (true) {
								if (isActive.not()) {
									break
								}

								delay(2.seconds)

								withContext(Dispatchers.Main) {
									handleIntent(
										intent = ArticleDetailsIntent.moveToNextImage(
											currentIndex = state.selectedImageIndex,
											imagesSize = sizeOfImages,
										)
									)
								}
							}
						}
					}
				}
			}
		}
	}

	override fun onCleared() {
		jobOfLoadDataAndObserveIt?.cancel()
		jobOfLoadDataAndObserveIt = null

		super.onCleared()
	}

}
