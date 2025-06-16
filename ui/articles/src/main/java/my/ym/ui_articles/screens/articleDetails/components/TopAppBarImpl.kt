package my.ym.ui_articles.screens.articleDetails.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.only
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.ym.ui_articles.R
import my.ym.ui_articles.screens.articleDetails.ArticleDetailsScreenScope
import my.ym.ui_shared.theme.ThemeApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ArticleDetailsScreenScope.TopAppBarImpl(
	modifier: Modifier = Modifier,

	goToPreviousScreen: () -> Unit,

	title: String,

	subtitle: String?,
) {
	TopAppBar(
		modifier = modifier.fillMaxWidth()
			.shadow(
				elevation = 4.dp,
				shape = RectangleShape,
				ambientColor = ThemeApp.colorScheme.onBackground,
				spotColor = ThemeApp.colorScheme.onBackground,
			),
		title = {
			Column {
				Text(
					text = title,
					style = MaterialTheme.typography.titleLarge,
					maxLines = 1,
					overflow = TextOverflow.Ellipsis
				)

				if (subtitle != null) {
					Text(
						text = subtitle,
						style = MaterialTheme.typography.bodyMedium,
						maxLines = 1,
						overflow = TextOverflow.Ellipsis
					)
				}
			}
		},
		navigationIcon = {
			IconButton(
				onClick = goToPreviousScreen,
				colors = IconButtonDefaults.iconButtonColors().let { colors ->
					colors.copy(
						disabledContentColor = colors.contentColor,
						disabledContainerColor = colors.containerColor
					)
				}
			) {
				Icon(
					imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
					contentDescription = stringResource(R.string.information)
				)
			}
		},
		windowInsets = TopAppBarDefaults.windowInsets.only(WindowInsetsSides.Horizontal)
	)
}

@Preview(showBackground = true)
@Composable
private fun Sample() = ThemeApp {
	ArticleDetailsScreenScope.apply {
		Column(
			modifier = Modifier.fillMaxSize()
		) {
			TopAppBarImpl(
				goToPreviousScreen = {},

				title = "Development",

				subtitle = "By Mohamed Alaa"
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun Sample2() = ThemeApp {
	ArticleDetailsScreenScope.apply {
		Column(
			modifier = Modifier.fillMaxSize()
		) {
			TopAppBarImpl(
				goToPreviousScreen = {},

				title = "Loading...",

				subtitle = null
			)
		}
	}
}
