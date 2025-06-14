package my.ym.ui_articles.screens.mostPopularArticles

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

fun NavGraphBuilder.composableOfMostPopularArticlesScreen(
	goToArticleDetailsScreen: (id: Long) -> Unit,
) {
	composable<MostPopularArticlesDestination> {
		val viewModel = hiltViewModel<MostPopularArticlesViewModel>()

		MostPopularArticlesScreen(
			goToArticleDetailsScreen = goToArticleDetailsScreen,

			handleIntent = viewModel::handleIntent,

			state = viewModel.state,
		)
	}
}

fun NavController.navToMostPopularArticlesScreen() {
	navigate(route = MostPopularArticlesDestination) {
		popUpTo(route = MostPopularArticlesDestination::class) {
			inclusive = true
		}
	}
}
