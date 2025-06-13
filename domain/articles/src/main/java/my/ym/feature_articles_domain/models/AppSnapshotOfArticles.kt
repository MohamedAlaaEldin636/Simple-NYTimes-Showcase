package my.ym.feature_articles_domain.models

import java.time.LocalDateTime

data class AppSnapshotOfArticles(
	val fetchedFromApiAt: LocalDateTime,
	val usedCache: Boolean,
	val articles: List<AppViewedArticle>,
)
