package my.ym.data_articles.local.database.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * - Has Many to Many relation with [DbEntityViewedArticle] via [DbEntityRelationViewedArticleAndKeyword]
 */
@Entity(
	tableName = "keyword",
	indices = [
		Index(
			value = ["keyword"],
			unique = true,
		),
	]
)
data class DbEntityKeyword(
	@PrimaryKey(autoGenerate = true)
	val id: Long = 0,

	val keyword: String,
)
