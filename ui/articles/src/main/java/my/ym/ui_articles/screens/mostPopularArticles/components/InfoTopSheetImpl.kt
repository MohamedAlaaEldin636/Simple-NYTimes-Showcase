package my.ym.ui_articles.screens.mostPopularArticles.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.ym.ui_articles.R
import my.ym.ui_articles.screens.mostPopularArticles.MostPopularArticlesScreenScope
import my.ym.ui_shared.BuildConfig
import my.ym.ui_shared.theme.ThemeApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun MostPopularArticlesScreenScope.InfoTopSheetImpl(
	modifier: Modifier = Modifier,

	onDismissRequest: () -> Unit,

	isInfoTopSheetShown: Boolean,
) {
	Column(
		modifier = modifier,
	) {
		AnimatedVisibility(
			visible = isInfoTopSheetShown,
			enter = expandVertically(),
			exit = shrinkVertically()
		) {
			val shape = RoundedCornerShape(
				topStart = 0.dp, topEnd = 0.dp, bottomStart = 16.dp, bottomEnd = 16.dp
			)

			Column(
				modifier = Modifier
					.fillMaxWidth()
					.shadow(
						elevation = 4.dp,
						shape = shape,
						ambientColor = ThemeApp.colorScheme.onBackground,
						spotColor = ThemeApp.colorScheme.onBackground,
					)
					.background(color = ThemeApp.colorScheme.background, shape = shape)
					.padding(all = 16.dp),
				verticalArrangement = Arrangement.spacedBy(16.dp)
			) {
				Row(
					horizontalArrangement = Arrangement.spacedBy(16.dp)
				) {
					Text(
						modifier = Modifier.weight(1f),
						text = buildAnnotatedString {
							append(stringResource(R.string.most_popular_article_screen_info_text_1))
						},
						style = ThemeApp.typography.titleMedium,
						textAlign = TextAlign.Center
					)
				}

				Text(
					text = buildAnnotatedString {
						appendLine(stringResource(R.string.most_popular_article_screen_info_text_2))

						withLink(
							link = LinkAnnotation.Url(
								url = BuildConfig.PROJECT_GITHUB_REPO_LINK,
								styles = TextLinkStyles(
									style = SpanStyle(
										textDecoration = TextDecoration.Underline,
										color = Color.Blue,
										fontStyle = FontStyle.Italic
									)
								)
							),
						) {
							append(stringResource(R.string.check_the_github_repo_from_here))
						}
					},
					style = ThemeApp.typography.bodyLarge,
					textAlign = TextAlign.Center
				)

				Button(
					modifier = Modifier.fillMaxWidth(),
					onClick = onDismissRequest
				) {
					Text(
						text = stringResource(R.string.ok),
					)
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun Sample() = ThemeApp {
	MostPopularArticlesScreenScope.apply {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.background(color = ThemeApp.colorScheme.scrim.copy(alpha = 0.5f))
		) {
			InfoTopSheetImpl(
				onDismissRequest = {},

				isInfoTopSheetShown = true,
			)
		}
	}
}
