package my.ym.data_articles.models.utils

import my.ym.data_articles.models.ApiMedia
import my.ym.domain_articles.models.AppMedia

fun ApiMedia.toAppMedia(): AppMedia {
	return AppMedia(
		type = if (isImage) AppMedia.Type.Image else AppMedia.Type.Other,
		caption = caption.orEmpty(),
		copyright = copyright.orEmpty(),
		listOfMediaMetadata = listOfMediaMetadata.orEmpty().map { it.toAppMediaMetadata() }
	)
}
