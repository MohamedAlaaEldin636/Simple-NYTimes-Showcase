package my.ym.feature_articles_data.models.utils

import my.ym.feature_articles_data.models.ApiMedia
import my.ym.feature_articles_domain.models.AppMedia

fun ApiMedia.toAppMedia(): AppMedia {
	return AppMedia(
		type = if (isImage) AppMedia.Type.Image else AppMedia.Type.Other,
		caption = caption.orEmpty(),
		copyright = copyright.orEmpty(),
		mediaMetadata = mediaMetadata.orEmpty().map { it.toAppMediaMetadata() }
	)
}
