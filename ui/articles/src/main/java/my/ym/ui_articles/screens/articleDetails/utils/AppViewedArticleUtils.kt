package my.ym.ui_articles.screens.articleDetails.utils

import my.ym.domain_articles.models.AppViewedArticle
import my.ym.ui_articles.screens.articleDetails.models.AppMediaMetadataWithType

fun AppViewedArticle.toListOfAppMediaMetadataWithType(): List<AppMediaMetadataWithType> {
	return listOfMedia.flatMap { media ->
		media.listOfMediaMetadata.map {
			AppMediaMetadataWithType(
				metadata = it,
				type = media.type,
			)
		}
	}
}
