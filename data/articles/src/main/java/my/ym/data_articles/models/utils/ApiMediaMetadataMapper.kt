package my.ym.data_articles.models.utils

import my.ym.data_articles.models.ApiMediaMetadata
import my.ym.domain_articles.models.AppMediaMetadata

fun ApiMediaMetadata.toAppMediaMetadata(): AppMediaMetadata {
	return AppMediaMetadata(
		url = url.orEmpty()
	)
}
