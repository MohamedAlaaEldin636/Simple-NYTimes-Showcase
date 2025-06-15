package my.ym.data_articles.models.utils

import my.ym.data_articles.models.ApiSnapshotOfArticles
import my.ym.domain_articles.models.AppSnapshotOfArticles
import java.time.LocalDateTime

fun ApiSnapshotOfArticles.toAppSnapshotOfArticles(): AppSnapshotOfArticles {
	return AppSnapshotOfArticles(
		fetchedFromApiAt = LocalDateTime.now(),
		fetchFailureReason = null,
		articles = results.orEmpty().map {
			it.toAppViewedArticle()
		},
	)
}
