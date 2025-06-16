package my.ym.data_articles.models.utils

import my.ym.data_articles.local.database.entities.models.DbViewedArticleWithKeywords
import my.ym.domain_articles.models.AppViewedArticle

internal fun DbViewedArticleWithKeywords.toAppViewedArticle(): AppViewedArticle {
	return AppViewedArticle(
		id = viewedArticle.id,
		title = viewedArticle.title,
		summary = viewedArticle.summary,
		section = viewedArticle.section,
		byLine = viewedArticle.byLine,
		publishedAt = viewedArticle.publishedAt,
		lastUpdatedAt = viewedArticle.lastUpdatedAt,
		keywords = keywords.map {
			it.keyword
		},
		listOfMedia = viewedArticle.listOfMedia.map {
			it.toAppMedia()
		}
	)
}
