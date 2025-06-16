package my.ym.data_articles.models.utils

import my.ym.data_articles.local.database.entities.DbEntitySnapshotOfViewedArticles
import my.ym.domain_articles.models.AppSnapshotOfArticles

fun AppSnapshotOfArticles.toDbEntitySnapshotOfViewedArticles(): DbEntitySnapshotOfViewedArticles {
	return DbEntitySnapshotOfViewedArticles(
		fetchedFromApiAt = fetchedFromApiAt,
	)
}
