package my.ym.data_articles.models.utils

import my.ym.data_articles.local.database.entities.models.DbMediaMetadata
import my.ym.domain_articles.models.AppMediaMetadata

fun AppMediaMetadata.toDbMediaMetadata(): DbMediaMetadata {
	return DbMediaMetadata(
		url = url
	)
}

fun DbMediaMetadata.toAppMediaMetadata(): AppMediaMetadata {
	return AppMediaMetadata(
		url = url
	)
}
