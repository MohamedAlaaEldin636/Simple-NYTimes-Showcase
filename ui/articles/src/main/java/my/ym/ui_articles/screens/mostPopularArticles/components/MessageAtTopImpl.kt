package my.ym.ui_articles.screens.mostPopularArticles.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import my.ym.ui_articles.R
import my.ym.ui_articles.screens.mostPopularArticles.MostPopularArticlesScreenScope
import my.ym.ui_shared.theme.ThemeApp

@Composable
internal fun MostPopularArticlesScreenScope.MessageAtTopImpl(
	modifier: Modifier = Modifier,
	messageAtTop: String?,
) {
	Column(
		modifier = modifier
			.fillMaxWidth()
			.padding(all = 4.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
	) {
		if (!messageAtTop.isNullOrEmpty()) {
			Text(
				modifier = Modifier.fillMaxWidth(),
				text = messageAtTop,
				textAlign = TextAlign.Center,
				style = ThemeApp.typography.bodyMedium
			)
		}

		Text(
			modifier = Modifier.fillMaxWidth(),
			text = stringResource(R.string.you_can_pull_to_refresh),
			textAlign = TextAlign.Center,
			style = ThemeApp.typography.bodySmall
		)
	}
}
