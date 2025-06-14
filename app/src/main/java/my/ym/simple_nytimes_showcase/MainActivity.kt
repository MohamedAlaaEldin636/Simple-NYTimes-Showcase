package my.ym.simple_nytimes_showcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import my.ym.ui_articles.screens.mostPopularArticles.MostPopularArticlesDestination
import my.ym.ui_articles.screens.mostPopularArticles.composableOfMostPopularArticlesScreen
import my.ym.ui_shared.theme.ThemeApp

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		enableEdgeToEdge()

		setContent {
			ThemeApp {
				Scaffold(
					modifier = Modifier.fillMaxSize(),
				) { innerPadding ->
					val navController = rememberNavController()

					NavHost(
						modifier = Modifier
							.padding(paddingValues = innerPadding)
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

}
