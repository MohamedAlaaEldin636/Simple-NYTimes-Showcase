package my.ym.ui_articles.screens.articleDetails

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.composableOfArticleDetailsScreen(
	goToPreviousScreen: () -> Unit,
) {
	composable<ArticleDetailsDestination> {
		val viewModel = hiltViewModel<ArticleDetailsViewModel>()

		ArticleDetailsScreen(
			goToPreviousScreen = goToPreviousScreen,

			handleIntent = viewModel::handleIntent,

			state = viewModel.state,
		)
	}
}

fun NavController.navToArticleDetailsScreen(articleId: Long) {
	navigate(route = ArticleDetailsDestination(id = articleId))
}
