package my.ym.ui_articles.screens.articleDetails.models

import my.ym.domain_articles.models.AppMedia
import my.ym.domain_articles.models.AppMediaMetadata

data class AppMediaMetadataWithType(
	val metadata: AppMediaMetadata,
	val type: AppMedia.Type,
) {
	val isImage: Boolean get() = type == AppMedia.Type.Image
}
