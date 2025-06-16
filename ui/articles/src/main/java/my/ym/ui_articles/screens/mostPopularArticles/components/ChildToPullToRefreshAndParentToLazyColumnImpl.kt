package my.ym.ui_articles.screens.mostPopularArticles.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import my.ym.ui_articles.R
import my.ym.ui_articles.screens.mostPopularArticles.MostPopularArticlesIntent
import my.ym.ui_articles.screens.mostPopularArticles.MostPopularArticlesScreenScope
import my.ym.ui_articles.screens.mostPopularArticles.MostPopularArticlesState
import my.ym.ui_shared.theme.ThemeApp
import java.time.LocalDateTime

@Composable
internal fun MostPopularArticlesScreenScope.ChildToPullToRefreshAndParentToLazyColumnImpl(
	lazyColumnImpl: @Composable () -> Unit,

	handleIntent: (intent: MostPopularArticlesIntent) -> Unit,

	state: MostPopularArticlesState,
) {
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
}
