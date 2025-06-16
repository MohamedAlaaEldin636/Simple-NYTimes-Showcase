package my.ym.data_articles.local.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import my.ym.data_articles.local.database.entities.models.DbMedia
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * - Has Many to Many relation with [DbEntityKeyword] via [DbEntityRelationViewedArticleAndKeyword]
 */
@Entity(tableName = "viewed_articles")
data class DbEntityViewedArticle(
	@PrimaryKey val id: Long,

	val title: String,
	val summary: String,

	/** Ex. Sports, Opinion */
	val section: String,
	/** Ex. By Mohamed Alaa */
	@ColumnInfo(name = "by_line")
	val byLine: String,

	@ColumnInfo(name = "published_at")
	val publishedAt: LocalDate?,
	@ColumnInfo(name = "last_updated_at")
	val lastUpdatedAt: LocalDateTime?,

	@ColumnInfo(name = "list_of_media")
	val listOfMedia: List<DbMedia>,
)
