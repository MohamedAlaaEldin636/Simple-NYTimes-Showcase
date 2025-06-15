package my.ym.data_articles.models.utils

import my.ym.data_shared.models.ApiDateAndTimeFormat
import my.ym.data_shared.models.ApiDateFormat
import my.ym.data_articles.models.ApiViewedArticle
import my.ym.domain_articles.models.AppViewedArticle

private const val SEMI_COLON = ';'

fun ApiViewedArticle.toAppViewedArticle(): AppViewedArticle {
	return AppViewedArticle(
		id = id ?: 0,
		title = title.orEmpty(),
		summary = summary.orEmpty(),
		section = section.orEmpty(),
		byLine = byLine.orEmpty(),
		publishedAt = ApiDateFormat(publishedDate).toLocalDateOrNull(),
		lastUpdatedAt = ApiDateAndTimeFormat(lastUpdatedDateAndTime).toLocalDateTimeOrNull(),
		keywords = keywords.orEmpty().split(SEMI_COLON),
		listOfMedia = listOfMedia.orEmpty().map { it.toAppMedia() }
	)
}
