package my.ym.ui_articles.screens.mostPopularArticles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import my.ym.core_kotlin.executeIfNotNull
import my.ym.domain_articles.models.AppSnapshotOfArticles
import my.ym.domain_articles.models.AppViewedArticle
import my.ym.domain_shared.models.AppResult
import my.ym.ui_articles.screens.mostPopularArticles.components.ArticleItemImpl
import my.ym.ui_articles.screens.mostPopularArticles.components.ChildToPullToRefreshAndParentToLazyColumnImpl
import my.ym.ui_articles.screens.mostPopularArticles.components.InfoTopSheetContainerImpl
import my.ym.ui_articles.screens.mostPopularArticles.components.MessageAtTopImpl
import my.ym.ui_articles.screens.mostPopularArticles.components.TopAppBarImpl
import my.ym.ui_shared.components.PullToRefreshLazyColumn
import my.ym.ui_shared.theme.ThemeApp
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.seconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MostPopularArticlesScreen(
	goToArticleDetailsScreen: (id: Long) -> Unit,

	handleIntent: (intent: MostPopularArticlesIntent) -> Unit,

	state: MostPopularArticlesState,
) = MostPopularArticlesScreenScope.executeIfNotNull {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(color = ThemeApp.colorScheme.background),
	) {
		TopAppBarImpl(
			showInfoTopSheet = { handleIntent(MostPopularArticlesIntent.showInfoTopSheet()) },

			isInfoTopSheetShown = state.showInfoTopSheet,
		)

		Box(
			modifier = Modifier
				.fillMaxWidth()
				.weight(1f),
		) {
			InfoTopSheetContainerImpl(
				boxScope = this,
				handleIntent = handleIntent,
				state = state,
			)

			PullToRefreshLazyColumn(
				modifier = Modifier.fillMaxSize(),

				pullToRefreshIsRefreshing = state.loadingStatus == MostPopularArticlesState.LoadingStatus.PullingToRefresh,
				pullToRefreshOnRefresh = {
					handleIntent(MostPopularArticlesIntent.PullToRefresh)
				},

				childToPullToRefreshAndParentToLazyColumn = { lazyColumnImpl ->
					ChildToPullToRefreshAndParentToLazyColumnImpl(
						lazyColumnImpl = lazyColumnImpl,

						handleIntent = handleIntent,

						state = state,
					)
				},

				lazyColumnContentPadding = PaddingValues(all = 8.dp),
				lazyColumnVerticalArrangement = Arrangement.spacedBy(space = 8.dp)
			) {
				item {
					MessageAtTopImpl(
						messageAtTop = state.messageAtTop
					)
				}

				if (state.data is MostPopularArticlesState.Data.Success) {
					val shape = RoundedCornerShape(size = 16.dp)

					items(items = state.data.snapshotOfArticles.articles) { article ->
						ArticleItemImpl(
							shape = shape,

							goToArticleDetailsScreen = goToArticleDetailsScreen,

							article = article,
						)
					}
				}
			}
		}
	}
}

@Preview
@Composable
private fun Sample() = ThemeApp {
	val dummyArticles = List(size = 20) {
		AppViewedArticle(
			id = it.inc().toLong(),
			title = "Title $it",
			summary = "Summary $it",
			section = "Section $it",
			byLine = "By Name $it",
			publishedAt = LocalDate.now().minusDays(it.inc().toLong()),
			lastUpdatedAt = LocalDateTime.now().minusDays(it.toLong()),
			keywords = List(size = 10) { innerIt -> "Keyword $innerIt" },
			listOfMedia = emptyList()
		)
	}

	var loadingStatus by remember {
		mutableStateOf(MostPopularArticlesState.LoadingStatus.Other)
	}

	LaunchedEffect(key1 = Unit) {
		delay(1.5.seconds)

		loadingStatus = MostPopularArticlesState.LoadingStatus.None
	}

	var showInfoTopSheet by remember { mutableStateOf(false) }

	var showMessageAtBottom by remember { mutableStateOf(true) }

	val defaultMessageAtBottom = MostPopularArticlesState.MessageWithType(
		message = "No Internet Connection\nPlease Try Again",
		type = MostPopularArticlesState.MessageType.Warning,
	)

	var showSuccessData by remember { mutableStateOf(false) }

	var showedEmptyDataOnce by remember { mutableStateOf(false) }
	var showedOldCacheOnce by remember { mutableStateOf(false) }

	val coroutineScope = rememberCoroutineScope()

	LaunchedEffect(key1 = showMessageAtBottom) {
		if (showMessageAtBottom.not()) {
			delay(1.seconds)

			showMessageAtBottom = true
		}
	}

	MostPopularArticlesScreen(
		goToArticleDetailsScreen = {  },

		handleIntent = { intent ->
			when (intent) {
				is MostPopularArticlesIntent.ChangeVisibilityOfInfoTopSheet -> {
					showInfoTopSheet = intent.show
				}
				MostPopularArticlesIntent.HideMessageAtBottom -> {
					showMessageAtBottom = false
				}
				MostPopularArticlesIntent.PullToRefresh -> {
					loadingStatus = MostPopularArticlesState.LoadingStatus.PullingToRefresh

					coroutineScope.launch {
						// Mock loading inshallah
						delay(1.seconds)

						loadingStatus = MostPopularArticlesState.LoadingStatus.None
					}
				}
				MostPopularArticlesIntent.RetryToFetchData -> {
					coroutineScope.launch {
						loadingStatus = MostPopularArticlesState.LoadingStatus.Other

						// Mock loading inshallah
						delay(1.seconds)

						loadingStatus = MostPopularArticlesState.LoadingStatus.None

						when {
							showSuccessData.not() -> {
								showSuccessData = true
							}
							showedOldCacheOnce.not() -> {
								showedOldCacheOnce = true
							}
							showedEmptyDataOnce.not() -> {
								showedEmptyDataOnce = true
							}
						}
					}
				}
			}
		},

		state = MostPopularArticlesState(
			loadingStatus = loadingStatus,
			data = if (showSuccessData) {
				if (showedOldCacheOnce) {
					if (showedEmptyDataOnce) {
						MostPopularArticlesState.Data.Success(
							snapshotOfArticles = AppSnapshotOfArticles(
								fetchedFromApiAt = LocalDateTime.now(),
								fetchFailureReason = null,
								articles = dummyArticles,
							),
						)
					}else {
						MostPopularArticlesState.Data.Success(
							snapshotOfArticles = AppSnapshotOfArticles(
								fetchedFromApiAt = LocalDateTime.now(),
								fetchFailureReason = null,
								articles = emptyList(),
							),
						)
					}
				}else {
					MostPopularArticlesState.Data.Success(
						snapshotOfArticles = AppSnapshotOfArticles(
							fetchedFromApiAt = LocalDateTime.now().minusDays(2),
							fetchFailureReason = AppResult.Failure.Reason.PoorInternetConnection,
							articles = dummyArticles,
						),
					)
				}
			}else {
				MostPopularArticlesState.Data.Error(
					msg = "Poor Internet Connection\nPlease Try Again"
				)
			},

			messageAtTop = "Updated 1 min ago",
			messageAtBottom = if (showMessageAtBottom.not()) null else defaultMessageAtBottom,
			previousMessageAtBottom = defaultMessageAtBottom,

			showInfoTopSheet = showInfoTopSheet
		)
	)
}
