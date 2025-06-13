package my.ym.domain_articles.models

import java.time.LocalDateTime

data class AppSnapshotOfArticles(
	val fetchedFromApiAt: LocalDateTime,
	val usedCache: Boolean,
	val articles: List<AppViewedArticle>,
)
