package my.ym.ui_articles.screens.articleDetails.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import my.ym.ui_articles.screens.articleDetails.ArticleDetailsScreenScope

@Composable
internal fun ArticleDetailsScreenScope.KeywordsImpl(keywords: List<String>?) {
	keywords?.also { keywords ->
		if (keywords.isNotEmpty()) {
			FlowRow(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.spacedBy(8.dp),
				verticalArrangement = Arrangement.spacedBy(8.dp),
			) {
				keywords.forEach {
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
