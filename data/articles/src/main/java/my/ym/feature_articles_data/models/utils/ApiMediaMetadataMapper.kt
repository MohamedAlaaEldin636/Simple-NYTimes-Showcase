package my.ym.feature_articles_data.models.utils

import my.ym.feature_articles_data.models.ApiMediaMetadata
import my.ym.feature_articles_domain.models.AppMediaMetadata

fun ApiMediaMetadata.toAppMediaMetadata(): AppMediaMetadata {
	return AppMediaMetadata(
		url = url.orEmpty()
	)
}
