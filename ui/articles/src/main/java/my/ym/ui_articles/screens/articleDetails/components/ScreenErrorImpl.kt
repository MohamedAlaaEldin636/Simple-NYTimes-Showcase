package my.ym.ui_articles.screens.articleDetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import my.ym.domain_shared.models.AppResult
import my.ym.ui_articles.screens.articleDetails.ArticleDetailsScreenScope
import my.ym.ui_articles.R
import my.ym.ui_shared.theme.ThemeApp
import my.ym.ui_shared.utils.getLocalizedText

@Composable
internal fun ArticleDetailsScreenScope.ScreenErrorImpl(
	retryToFetchData: () -> Unit,

	reason: AppResult.Failure.Reason,
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(all = 16.dp),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.spacedBy(
			space = 16.dp,
			alignment = Alignment.CenterVertically,
		)
	) {
		Text(
			text = reason.getLocalizedText(),
			style = ThemeApp.typography.bodyLarge,
			color = ThemeApp.colorScheme.onBackground,
			textAlign = TextAlign.Center,
		)

		Button(
			onClick = retryToFetchData,
		) {
			Text(
				text = stringResource(id = R.string.refresh_the_data),
			)
		}
	}
}
