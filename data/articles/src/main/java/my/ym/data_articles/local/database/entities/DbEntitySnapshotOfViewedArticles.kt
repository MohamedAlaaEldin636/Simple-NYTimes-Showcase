package my.ym.data_articles.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

/**
 * - Has Many to Many relation with [DbEntityViewedArticle] via [DbEntityRelationSnapshotOfViewedArticlesAndViewedArticle]
 */
@Entity(tableName = "snapshot_of_viewed_articles")
data class DbEntitySnapshotOfViewedArticles(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,

	@ColumnInfo(name = "fetched_from_api_at")
	val fetchedFromApiAt: LocalDateTime,
)
