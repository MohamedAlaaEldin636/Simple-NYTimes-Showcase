package my.ym.ui_articles.screens.mostPopularArticles.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import my.ym.ui_articles.screens.mostPopularArticles.MostPopularArticlesIntent
import my.ym.ui_articles.screens.mostPopularArticles.MostPopularArticlesScreenScope
import my.ym.ui_articles.screens.mostPopularArticles.MostPopularArticlesState
import my.ym.ui_shared.theme.ThemeApp

@Composable
internal fun MostPopularArticlesScreenScope.InfoTopSheetContainerImpl(
	boxScope: BoxScope,

	handleIntent: (intent: MostPopularArticlesIntent) -> Unit,

	state: MostPopularArticlesState,
) {
	boxScope.apply {
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
	}
}
