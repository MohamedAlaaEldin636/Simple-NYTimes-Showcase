package my.ym.data_articles.models.utils

import my.ym.data_articles.local.database.entities.DbEntityViewedArticle
import my.ym.domain_articles.models.AppViewedArticle

fun AppViewedArticle.toDbEntityViewedArticle(): DbEntityViewedArticle {
	return DbEntityViewedArticle(
		id = id,
		title = title,
		summary = summary,
		section = section,
		byLine = byLine,
		publishedAt = publishedAt,
		lastUpdatedAt = lastUpdatedAt,
		listOfMedia = listOfMedia.map {
			it.toDbMedia()
		}
	)
}

fun DbEntityViewedArticle.toAppViewedArticle(
	keywords: List<String>,
): AppViewedArticle {
	return AppViewedArticle(
		id = id,
		title = title,
		summary = summary,
		section = section,
		byLine = byLine,
		publishedAt = publishedAt,
		lastUpdatedAt = lastUpdatedAt,
		keywords = keywords,
		listOfMedia = listOfMedia.map {
			it.toAppMedia()
		}
	)
}
