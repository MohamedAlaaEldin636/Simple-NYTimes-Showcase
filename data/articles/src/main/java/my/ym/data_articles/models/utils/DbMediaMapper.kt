package my.ym.data_articles.models.utils

import my.ym.data_articles.local.database.entities.models.DbMedia
import my.ym.domain_articles.models.AppMedia

fun AppMedia.toDbMedia(): DbMedia {
	return DbMedia(
		type = type.toDbMediaType(),
		caption = caption,
		copyright = copyright,
		listOfMediaMetadata = this@toDbMedia.listOfMediaMetadata.map {
			it.toDbMediaMetadata()
		}
	)
}

fun DbMedia.toAppMedia(): AppMedia {
	return AppMedia(
		type = type.toAppMediaType(),
		caption = caption,
		copyright = copyright,
		listOfMediaMetadata = listOfMediaMetadata.map {
			it.toAppMediaMetadata()
		}
	)
}

private fun AppMedia.Type.toDbMediaType(): DbMedia.Type {
	return when (this) {
		AppMedia.Type.Image -> DbMedia.Type.Image
		AppMedia.Type.Other -> DbMedia.Type.Other
	}
}

private fun DbMedia.Type.toAppMediaType(): AppMedia.Type {
	return when (this) {
		DbMedia.Type.Image -> AppMedia.Type.Image
		DbMedia.Type.Other -> AppMedia.Type.Other
	}
}
