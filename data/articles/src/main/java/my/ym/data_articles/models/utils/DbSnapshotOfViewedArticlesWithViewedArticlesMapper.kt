package my.ym.data_articles.models.utils

import my.ym.data_articles.local.database.entities.DbEntityViewedArticle
import my.ym.data_articles.local.database.entities.models.DbSnapshotOfViewedArticlesWithViewedArticles
import my.ym.domain_articles.models.AppSnapshotOfArticles
import my.ym.domain_shared.models.AppResult

inline fun DbSnapshotOfViewedArticlesWithViewedArticles.toAppSnapshotOfArticles(
	fetchFailureReason: AppResult.Failure.Reason?,
	getKeywords: (DbEntityViewedArticle) -> List<String>,
): AppSnapshotOfArticles {
	return AppSnapshotOfArticles(
		fetchedFromApiAt = snapshotOfViewedArticles.fetchedFromApiAt,
		fetchFailureReason = fetchFailureReason,
		articles = viewedArticles.map {
			it.toAppViewedArticle(keywords = getKeywords(it))
		}
	)
}
