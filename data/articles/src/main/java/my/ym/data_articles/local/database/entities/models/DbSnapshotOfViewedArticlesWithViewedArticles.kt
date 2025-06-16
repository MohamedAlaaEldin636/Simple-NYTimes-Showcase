package my.ym.data_articles.local.database.entities.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import my.ym.data_articles.local.database.entities.DbEntityRelationSnapshotOfViewedArticlesAndViewedArticle
import my.ym.data_articles.local.database.entities.DbEntitySnapshotOfViewedArticles
import my.ym.data_articles.local.database.entities.DbEntityViewedArticle

data class DbSnapshotOfViewedArticlesWithViewedArticles(
	@Embedded
	val snapshotOfViewedArticles: DbEntitySnapshotOfViewedArticles,
	@Relation(
		parentColumn = "id", // The 'id' of the DbEntitySnapshotOfViewedArticles entity
		entityColumn = "id", // The 'id' of the DbEntityViewedArticle entity
		associateBy = Junction(
			DbEntityRelationSnapshotOfViewedArticlesAndViewedArticle::class,
			// The column in DbEntityRelationSnapshotOfViewedArticlesAndViewedArticle that references DbEntitySnapshotOfViewedArticles id
			parentColumn = "snapshotOfViewedArticlesId",
			// The column in DbEntityRelationSnapshotOfViewedArticlesAndViewedArticle that references DbEntityViewedArticle's id
			entityColumn = "viewedArticleId",
		)
	)
	val viewedArticles: List<DbEntityViewedArticle>
)
