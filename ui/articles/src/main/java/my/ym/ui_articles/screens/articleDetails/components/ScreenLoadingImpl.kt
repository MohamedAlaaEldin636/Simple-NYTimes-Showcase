package my.ym.ui_articles.screens.articleDetails.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import my.ym.ui_articles.screens.articleDetails.ArticleDetailsScreenScope

@Composable
internal fun ArticleDetailsScreenScope.ScreenLoadingImpl() {
	Box(
		modifier = Modifier.fillMaxSize(),
		contentAlignment = Alignment.Center,
	) {
		CircularProgressIndicator()
	}
}
