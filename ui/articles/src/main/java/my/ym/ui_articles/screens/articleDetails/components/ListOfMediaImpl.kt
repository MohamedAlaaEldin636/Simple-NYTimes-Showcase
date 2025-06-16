package my.ym.ui_articles.screens.articleDetails.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import my.ym.domain_articles.models.AppMedia
import my.ym.domain_articles.models.AppMediaMetadata
import my.ym.domain_articles.models.AppViewedArticle
import my.ym.ui_articles.R
import my.ym.ui_articles.screens.articleDetails.ArticleDetailsScreenScope
import my.ym.ui_articles.screens.articleDetails.models.AppMediaMetadataWithType
import my.ym.ui_articles.screens.articleDetails.utils.toListOfAppMediaMetadataWithType
import my.ym.ui_shared.theme.ThemeApp

@Composable
internal fun ArticleDetailsScreenScope.ListOfMediaImpl(
	selectImage: (index: Int) -> Unit,

	data: AppViewedArticle,
	selectedImageIndex: Int,
) {
	val listOfMedia = data.toListOfAppMediaMetadataWithType()
	if (listOfMedia.isNotEmpty()) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.height(300.dp)
				.shadow(
					elevation = 4.dp,
					shape = RectangleShape,
					ambientColor = ThemeApp.colorScheme.onBackground,
					spotColor = ThemeApp.colorScheme.onBackground,
				)
				.background(color = ThemeApp.colorScheme.background),
		) {
			val mediaWithType = listOfMedia.getOrNull(
				index = selectedImageIndex
			) ?: AppMediaMetadataWithType(
				metadata = AppMediaMetadata(url = ""),
				type = AppMedia.Type.Other,
			)

			if (mediaWithType.type == AppMedia.Type.Other) {
				Text(
					modifier = Modifier
						.align(alignment = Alignment.Center)
						.padding(all = 16.dp),
					text = buildAnnotatedString {
						val url = mediaWithType.metadata.url
						if (url.isEmpty()) {
							append(stringResource(R.string.empty_url))
						}else {
							withLink(
								link = LinkAnnotation.Url(
									url = url,
									styles = TextLinkStyles(
										style = SpanStyle(
											textDecoration = TextDecoration.Underline,
											color = Color.Blue,
											fontStyle = FontStyle.Italic
										)
									)
								),
							) {
								append(url)
							}
						}
					},
					textAlign = TextAlign.Center,
					color = ThemeApp.colorScheme.onBackground,
				)
			}else {
				SubcomposeAsyncImage(
					modifier = Modifier
						.fillMaxSize(),
					model = mediaWithType.metadata.url,
					contentDescription = null,
					contentScale = ContentScale.Crop,
					loading = {
						Box(
							modifier = Modifier.fillMaxSize(),
							contentAlignment = Alignment.Center,
						) {
							CircularProgressIndicator()
						}
					},
					error = {
						Box(
							modifier = Modifier.fillMaxSize(),
							contentAlignment = Alignment.Center,
						) {
							Text(
								modifier = Modifier
									.padding(all = 16.dp),
								text = stringResource(R.string.error_in_loading_the_image),
								textAlign = TextAlign.Center,
								color = ThemeApp.colorScheme.onBackground,
							)
						}
					}
				)
			}

			LazyRow(
				modifier = Modifier
					.fillMaxWidth()
					.align(alignment = Alignment.BottomCenter),
				contentPadding = PaddingValues(all = 16.dp),
				horizontalArrangement = Arrangement.spacedBy(
					space = 16.dp,
					alignment = Alignment.CenterHorizontally,
				),
				verticalAlignment = Alignment.CenterVertically,
			) {
				val shape = RoundedCornerShape(size = 12.dp)

				itemsIndexed(items = listOfMedia) { index, item ->
					val isSelected = index == selectedImageIndex

					val borderThickness by animateDpAsState(
						targetValue = if (isSelected) 4.dp else 2.dp
					)

					val borderColor by animateColorAsState(
						targetValue = if (isSelected) {
							ThemeApp.extendedColorScheme.success.color
						}else {
							ThemeApp.colorScheme.primary
						}
					)

					val size = 60.dp
					Box(
						modifier = Modifier
							.size(size = size)
							.clip(shape = shape)
							.background(color = ThemeApp.colorScheme.background, shape = shape)
							.border(
								width = borderThickness,
								color = borderColor,
								shape = shape,
							)
							.clickable {
								selectImage(index)
							}
					) {
						var state: AsyncImagePainter.State by remember {
							mutableStateOf(AsyncImagePainter.State.Loading(painter = null))
						}

						AsyncImage(
							modifier = Modifier.fillMaxSize(),
							model = item.metadata.url,
							contentDescription = null,
							onState = {
								state = it
							},
							contentScale = ContentScale.Crop
						)

						if (state is AsyncImagePainter.State.Loading) {
							Image(
								modifier = Modifier.align(alignment = Alignment.Center),
								contentDescription = null,
								imageVector = ImageVector.vectorResource(id = R.drawable.loading),
								alignment = Alignment.Center,
							)
						}else if (state is AsyncImagePainter.State.Error) {
							Image(
								modifier = Modifier.align(alignment = Alignment.Center),
								contentDescription = null,
								imageVector = ImageVector.vectorResource(id = R.drawable.broken_image),
								alignment = Alignment.Center,
							)
						}
					}
				}
			}
		}
	}
}
