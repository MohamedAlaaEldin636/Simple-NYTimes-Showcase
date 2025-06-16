package my.ym.ui_articles.screens.articleDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import my.ym.domain_articles.models.AppViewedArticle
import my.ym.ui_articles.R
import my.ym.ui_articles.screens.articleDetails.ArticleDetailsScreenScope
import my.ym.ui_shared.theme.ThemeApp
import my.ym.ui_shared.utils.getLocalizedText

@Composable
internal fun ArticleDetailsScreenScope.ScreenSuccessImpl(
	goToPreviousScreen: () -> Unit,

	selectImage: (index: Int) -> Unit,

	data: AppViewedArticle,
	selectedImageIndex: Int,
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.background(color = ThemeApp.colorScheme.background),
	) {
		TopAppBarImpl(
			goToPreviousScreen = goToPreviousScreen,

			title = data.section,

			subtitle = data.byLine,
		)

		ListOfMediaImpl(
			selectImage = selectImage,

			data = data,
			selectedImageIndex = selectedImageIndex,
		)

		Column(
			modifier = Modifier
				.fillMaxWidth()
				.weight(1f)
				.verticalScroll(rememberScrollState())
				.padding(all = 16.dp),
			verticalArrangement = Arrangement.spacedBy(space = 16.dp),
		) {
			TitleAndSubtitleImpl(
				title = stringResource(R.string.title),
				subtitle = data.title,
			)

			TitleAndSubtitleImpl(
				title = stringResource(R.string.summary),
				subtitle = data.summary,
			)

			data.publishedAt?.getLocalizedText()?.also {
				TitleAndSubtitleImpl(
					title = stringResource(R.string.published_at),
					subtitle = it,
				)
			}

			data.lastUpdatedAt?.getLocalizedText()?.also {
				TitleAndSubtitleImpl(
					title = stringResource(R.string.last_updated_at),
					subtitle = it,
				)
			}

			KeywordsImpl(keywords = data.keywords)
		}
	}
}
