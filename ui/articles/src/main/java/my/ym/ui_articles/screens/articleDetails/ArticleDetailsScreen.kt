package my.ym.ui_articles.screens.articleDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import my.ym.core_kotlin.executeIfNotNull
import my.ym.domain_articles.models.AppViewedArticle
import my.ym.ui_shared.theme.ThemeApp
import my.ym.domain_shared.models.AppResult
import my.ym.ui_articles.screens.articleDetails.components.ScreenErrorImpl
import my.ym.ui_articles.screens.articleDetails.components.ScreenLoadingImpl
import my.ym.ui_articles.screens.articleDetails.components.ScreenSuccessImpl

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArticleDetailsScreen(
	goToPreviousScreen: () -> Unit,

	handleIntent: (intent: ArticleDetailsIntent) -> Unit,

	state: ArticleDetailsState,
) = ArticleDetailsScreenScope.executeIfNotNull {
	Box(
		modifier = Modifier
			.fillMaxSize()
			.background(color = ThemeApp.colorScheme.background),
	) {
		when (state.appResult) {
			is AppResult.Loading -> ScreenLoadingImpl()
			is AppResult.Success -> ScreenSuccessImpl(
				goToPreviousScreen = goToPreviousScreen,

				selectImage = { index ->
					handleIntent(ArticleDetailsIntent.SelectImage(index = index))
				},

				data = state.appResult.data,

				selectedImageIndex = state.selectedImageIndex,
			)
			is AppResult.Failure -> ScreenErrorImpl(
				retryToFetchData = {
					handleIntent(ArticleDetailsIntent.RetryToFetchData)
				},

				reason = state.appResult.reason,
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun Sample() = ThemeApp {
	ArticleDetailsScreenScope.apply {
		Column(
			modifier = Modifier.fillMaxSize()
		) {
			ArticleDetailsScreen(
				goToPreviousScreen = {},

				handleIntent = {},

				state = ArticleDetailsState(
					appResult = AppResult.Success(
						data = AppViewedArticle(
							id = 0,
							title = "TODO()",
							summary = "TODO()",
							section = "TODO()",
							byLine = "TODO()",
							publishedAt = null/*"TODO()"*/,
							lastUpdatedAt = null/*"TODO()"*/,
							keywords = listOf("TODO()"),
							listOfMedia = emptyList()//TODO()
						),
					),
					//selectedImageIndex = ,
				)
			)
		}
	}
}
