package my.ym.ui_articles.screens.articleDetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import my.ym.ui_articles.screens.articleDetails.ArticleDetailsScreenScope
import my.ym.ui_shared.theme.ThemeApp

@Composable
internal fun ArticleDetailsScreenScope.TitleAndSubtitleImpl(
	title: String,
	subtitle: String,
) {
	Column {
		Text(
			text = title,
			color = ThemeApp.colorScheme.onBackground,
			style = ThemeApp.typography.bodyMedium,
		)

		Text(
			text = subtitle,
			color = ThemeApp.colorScheme.onBackground,
			style = ThemeApp.typography.bodyLarge,
		)
	}
}
