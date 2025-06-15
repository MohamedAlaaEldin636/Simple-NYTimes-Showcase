package my.ym.data_articles.local.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
	tableName = "relation_of_viewed_article_and_keyword",
	primaryKeys = ["viewedArticleId", "keywordId"],
	foreignKeys = [
		ForeignKey(
			entity = DbEntityViewedArticle::class,
			parentColumns = ["id"],
			childColumns = ["viewedArticleId"],
			onDelete = ForeignKey.CASCADE,
		),
		ForeignKey(
			entity = DbEntityKeyword::class,
			parentColumns = ["id"],
			childColumns = ["keywordId"],
			onDelete = ForeignKey.CASCADE,
		),
	],
)
data class DbEntityRelationViewedArticleAndKeyword(
	val viewedArticleId: Long,
	val keywordId: Long,
)
