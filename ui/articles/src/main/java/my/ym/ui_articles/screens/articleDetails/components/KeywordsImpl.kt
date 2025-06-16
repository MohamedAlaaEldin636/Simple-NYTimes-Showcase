package my.ym.ui_articles.screens.articleDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import my.ym.ui_articles.R
import my.ym.ui_articles.screens.articleDetails.ArticleDetailsScreenScope
import my.ym.ui_shared.theme.ThemeApp

@Composable
internal fun ArticleDetailsScreenScope.KeywordsImpl(keywords: List<String>?) {
	keywords?.also { keywords ->
		if (keywords.isNotEmpty()) {
			Column(
				verticalArrangement = Arrangement.spacedBy(space = 4.dp)
			) {
				Text(
					text = stringResource(R.string.keywords),
					color = ThemeApp.colorScheme.onBackground,
					style = ThemeApp.typography.bodyMedium,
					fontWeight = FontWeight.Bold
				)

				FlowRow(
					modifier = Modifier.fillMaxWidth(),
					horizontalArrangement = Arrangement.spacedBy(8.dp),
					verticalArrangement = Arrangement.spacedBy(8.dp),
				) {
					keywords.forEach {
						CompositionLocalProvider(
							LocalMinimumInteractiveComponentSize provides 0.dp
						) {
							SuggestionChip(
								enabled = false,
								onClick = {},
								label = {
									Text(
										text = it,
									)
								},
								colors = SuggestionChipDefaults.suggestionChipColors().let { colors ->
									colors.copy(
										disabledContainerColor = colors.containerColor,
										disabledLabelColor = colors.labelColor,
										disabledLeadingIconContentColor = colors.leadingIconContentColor,
										disabledTrailingIconContentColor = colors.trailingIconContentColor
									)
								},
							)
						}
					}
				}
			}
		}
	}
}
