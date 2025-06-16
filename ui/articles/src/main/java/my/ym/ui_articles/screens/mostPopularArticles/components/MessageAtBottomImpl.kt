package my.ym.ui_articles.screens.mostPopularArticles.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import my.ym.ui_articles.R
import my.ym.ui_articles.screens.mostPopularArticles.MostPopularArticlesScreenScope
import my.ym.ui_articles.screens.mostPopularArticles.MostPopularArticlesState
import my.ym.ui_shared.theme.ThemeApp
import kotlin.time.Duration.Companion.seconds

@Composable
internal fun MostPopularArticlesScreenScope.MessageAtBottomImpl(
	modifier: Modifier = Modifier,

	messageAtBottom: MostPopularArticlesState.MessageWithType,
	onDismissRequest: () -> Unit,
) {
	val (backgroundColor, contentColor) = when (messageAtBottom.type) {
		MostPopularArticlesState.MessageType.Normal -> {
			Color.Transparent to ThemeApp.colorScheme.onBackground
		}
		MostPopularArticlesState.MessageType.Success -> ThemeApp.extendedColorScheme.success.let {
			it.colorContainer to it.onColorContainer
		}
		MostPopularArticlesState.MessageType.Warning -> ThemeApp.extendedColorScheme.warning.let {
			it.colorContainer to it.onColorContainer
		}
		MostPopularArticlesState.MessageType.Error -> {
			ThemeApp.colorScheme.error to ThemeApp.colorScheme.onError
		}
	}

	Row(
		modifier = modifier
			.fillMaxWidth()
			.background(color = backgroundColor)
			.padding(vertical = 8.dp, horizontal = 16.dp),
		horizontalArrangement = Arrangement.spacedBy(space = 8.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		Text(
			modifier = Modifier
				.weight(1f),
			text = messageAtBottom.message,
			style = ThemeApp.typography.bodyMedium,
			color = contentColor,
		)

		val autoHide = messageAtBottom.type == MostPopularArticlesState.MessageType.Success
				|| messageAtBottom.type == MostPopularArticlesState.MessageType.Normal

		if (autoHide) {
			LaunchedEffect(key1 = Unit) {
				delay(3.seconds)

				onDismissRequest()
			}
		}else {
			Button(
				modifier = Modifier
					.defaultMinSize(minWidth = 0.dp, minHeight = 0.dp),
				onClick = onDismissRequest,
				shape = RoundedCornerShape(size = 12.dp),
				colors = ButtonDefaults.buttonColors(
					containerColor = ThemeApp.colorScheme.primary
				),
			) {
				Text(
					text = stringResource(R.string.dismiss),
					color = ThemeApp.colorScheme.onPrimary,
				)
			}
		}
	}
}
