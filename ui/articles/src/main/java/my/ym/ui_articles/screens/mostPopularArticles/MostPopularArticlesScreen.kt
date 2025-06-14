package my.ym.ui_articles.screens.mostPopularArticles

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import my.ym.core_kotlin.executeIfNotNull
import my.ym.domain_articles.models.AppSnapshotOfArticles
import my.ym.domain_articles.models.AppViewedArticle
import my.ym.domain_shared.models.AppResult
import my.ym.ui_articles.R
import my.ym.ui_articles.screens.mostPopularArticles.components.InfoTopSheetImpl
import my.ym.ui_articles.screens.mostPopularArticles.components.MessageAtBottomImpl
import my.ym.ui_articles.screens.mostPopularArticles.components.MessageAtTopImpl
import my.ym.ui_articles.screens.mostPopularArticles.components.TopAppBarImpl
import my.ym.ui_shared.components.PullToRefreshLazyColumn
import my.ym.ui_shared.theme.ThemeApp
import my.ym.ui_shared.utils.getLocalizedText
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.time.Duration.Companion.seconds

/** todo 1- don't need params and instead call viewModel from here walla mn el nav graph wala eh inshallah ?! */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostPopularArticlesScreen(
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
			val backgroundColor by animateColorAsState(
				targetValue = if (state.showInfoTopSheet) {
					ThemeApp.colorScheme.scrim.copy(alpha = 0.5f)
				}else {
					Color.Transparent
				}
			)

			Box(
				modifier = Modifier
					.matchParentSize()
					.zIndex(Float.MAX_VALUE)
					.background(color = backgroundColor)
					.let { modifier ->
						if (state.showInfoTopSheet.not()) modifier else modifier.clickable(
							indication = null,
							onClick = {
								handleIntent(MostPopularArticlesIntent.hideInfoTopSheet())
							},
							interactionSource = null
						)
					}
			)

			InfoTopSheetImpl(
				// To absorb from parent so that it won't be dismissed on being clicked.
				modifier = Modifier
					.zIndex(Float.MAX_VALUE)
					.clickable(
						indication = null,
						onClick = {},
						interactionSource = null
					),
				onDismissRequest = {
					handleIntent(MostPopularArticlesIntent.hideInfoTopSheet())
				},
				isInfoTopSheetShown = state.showInfoTopSheet
			)

			PullToRefreshLazyColumn(
				modifier = Modifier.fillMaxSize(),

				pullToRefreshIsRefreshing = state.loadingStatus == MostPopularArticlesState.LoadingStatus.PullingToRefresh,
				pullToRefreshOnRefresh = {
					handleIntent(MostPopularArticlesIntent.PullToRefresh)
				},

				childToPullToRefreshAndParentToLazyColumn = { lazyColumnImpl ->
					Column(
						modifier = Modifier.fillMaxSize(),
					) {
						Box(
							modifier = Modifier
								.fillMaxWidth()
								.weight(1f),
							contentAlignment = Alignment.Center,
						) {
							if (state.loadingStatus == MostPopularArticlesState.LoadingStatus.Other) {
								CircularProgressIndicator()
							}else {
								when (state.data) {
									is MostPopularArticlesState.Data.Success
										if state.data.snapshotOfArticles.fetchedFromApiAt.plusDays(1) > LocalDateTime.now() -> {
										if (state.data.snapshotOfArticles.articles.isNotEmpty()) {
											lazyColumnImpl()
										}else {
											Column(
												modifier = Modifier
													.fillMaxWidth()
													.padding(all = 16.dp),
												horizontalAlignment = Alignment.CenterHorizontally,
												verticalArrangement = Arrangement.spacedBy(space = 8.dp)
											) {
												Text(
													text = stringResource(R.string.empty_data_msg),
													style = ThemeApp.typography.bodyLarge,
													color = ThemeApp.colorScheme.onBackground,
													textAlign = TextAlign.Center,
												)

												Button(
													onClick = {
														handleIntent(
															MostPopularArticlesIntent.RetryToFetchData
														)
													}
												) {
													Text(
														text = stringResource(R.string.refresh_the_data),
													)
												}
											}
										}
									}
									else -> {
										Column(
											modifier = Modifier
												.fillMaxWidth()
												.padding(all = 16.dp),
											horizontalAlignment = Alignment.CenterHorizontally,
											verticalArrangement = Arrangement.spacedBy(space = 8.dp)
										) {
											val errorMsg = if (state.data is MostPopularArticlesState.Data.Error) {
												state.data.msg
											}else {
												stringResource(R.string.old_cached_data_and_unable_to_get_fresh_data_now)
											}

											Text(
												text = errorMsg,
												style = ThemeApp.typography.bodyLarge,
												color = ThemeApp.colorScheme.onBackground,
												textAlign = TextAlign.Center,
											)

											Button(
												onClick = {
													handleIntent(
														MostPopularArticlesIntent.RetryToFetchData
													)
												}
											) {
												Text(
													text = stringResource(R.string.try_again),
												)
											}
										}
									}
								}
							}
						}

						AnimatedVisibility(
							visible = state.messageAtBottom != null,
							enter = expandVertically(),
							exit = shrinkVertically()
						) {
							if (state.messageAtBottom != null) {
								MessageAtBottomImpl(
									messageAtBottom = state.messageAtBottom,
									onDismissRequest = {
										handleIntent(MostPopularArticlesIntent.HideMessageAtBottom)
									}
								)
							}else if (state.previousMessageAtBottom != null) {
								MessageAtBottomImpl(
									messageAtBottom = state.previousMessageAtBottom,
									onDismissRequest = {}
								)
							}
						}
					}
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
						Column(
							modifier = Modifier
								.fillMaxWidth()
								.shadow(
									elevation = 2.dp,
									shape = shape,
									ambientColor = ThemeApp.colorScheme.onBackground,
									spotColor = ThemeApp.colorScheme.onBackground,
								)
								.background(
									color = ThemeApp.colorScheme.background,
									shape = shape
								)
								.clickable {
									goToArticleDetailsScreen(article.id)
								}
								.padding(all = 16.dp),
							verticalArrangement = Arrangement.spacedBy(space = 4.dp),
						) {
							val publishedAt = article.publishedAt
							if (publishedAt != null) {
								Text(
									text = stringResource(R.string.published_at)
										.plus(" : ")
										.plus(publishedAt.getLocalizedText()),
									style = ThemeApp.typography.bodySmall,
									color = ThemeApp.colorScheme.onBackground
								)
							}

							Text(
								text = article.title,
								style = ThemeApp.typography.bodyLarge,
								color = ThemeApp.colorScheme.onBackground
							)

							Text(
								modifier = Modifier.align(alignment = Alignment.End),
								text = article.byLine,
								style = ThemeApp.typography.bodyMedium,
								color = ThemeApp.colorScheme.onBackground
							)

						}
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
			media = emptyList()
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
