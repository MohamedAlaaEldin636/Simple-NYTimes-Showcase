package my.ym.ui_articles.screens.mostPopularArticles.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import my.ym.domain_articles.models.AppViewedArticle
import my.ym.ui_articles.R
import my.ym.ui_articles.screens.mostPopularArticles.MostPopularArticlesScreenScope
import my.ym.ui_shared.theme.ThemeApp
import my.ym.ui_shared.utils.getLocalizedText

@Composable
internal fun MostPopularArticlesScreenScope.ArticleItemImpl(
	shape: Shape,

	goToArticleDetailsScreen: (Long) -> Unit,

	article: AppViewedArticle,
) {
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
