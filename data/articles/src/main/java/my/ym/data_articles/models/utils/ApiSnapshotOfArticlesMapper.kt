package my.ym.data_articles.models.utils

import my.ym.data_articles.models.ApiSnapshotOfArticles
import my.ym.domain_articles.models.AppSnapshotOfArticles
import java.time.LocalDateTime

fun ApiSnapshotOfArticles.toAppSnapshotOfArticles(): AppSnapshotOfArticles {
	return AppSnapshotOfArticles(
		fetchedFromApiAt = LocalDateTime.now(),
		// null since receiver is from backend so it's fresh inshallah,
		// since instance of Api... can and should only be granted from ApiService inshallah.
		fetchFailureReason = null,
		articles = results.orEmpty().map {
			it.toAppViewedArticle()
		},
	)
}
