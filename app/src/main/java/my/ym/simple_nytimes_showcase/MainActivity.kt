package my.ym.simple_nytimes_showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import my.ym.ui_articles.screens.mostPopularArticles.MostPopularArticlesDestination
import my.ym.ui_articles.screens.mostPopularArticles.composableOfMostPopularArticlesScreen
import my.ym.ui_shared.theme.ThemeApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		enableEdgeToEdge()

		setContent {
			ThemeApp {
				val navController = rememberNavController()

				NavHost(
					modifier = Modifier
						.systemBarsPadding()
						.fillMaxSize(),
					navController = navController,
					startDestination = MostPopularArticlesDestination
				) {
					composableOfMostPopularArticlesScreen(
						goToArticleDetailsScreen = { id ->
							TODO("id -> $id")
						}
					)
				}
			}
		}
	}

}
