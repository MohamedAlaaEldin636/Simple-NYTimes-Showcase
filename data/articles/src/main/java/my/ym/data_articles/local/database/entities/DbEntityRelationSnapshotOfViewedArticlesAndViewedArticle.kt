package my.ym.data_articles.local.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
	tableName = "relation_of_snapshot_of_viewed_articles_and_viewed_articles",
	primaryKeys = ["snapshotOfViewedArticlesId", "viewedArticleId"],
	foreignKeys = [

		ForeignKey(
			entity = DbEntitySnapshotOfViewedArticles::class,
			parentColumns = ["id"],
			childColumns = ["snapshotOfViewedArticlesId"],
			onDelete = ForeignKey.CASCADE,
		),
		ForeignKey(
			entity = DbEntityViewedArticle::class,
			parentColumns = ["id"],
			childColumns = ["viewedArticleId"],
			onDelete = ForeignKey.CASCADE,
		),
	],
)
data class DbEntityRelationSnapshotOfViewedArticlesAndViewedArticle(
	val snapshotOfViewedArticlesId: Long,
	val viewedArticleId: Long,
)
