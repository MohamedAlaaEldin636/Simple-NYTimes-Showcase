package my.ym.data_articles.local.database.entities.models

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import my.ym.data_articles.local.database.entities.DbEntityKeyword
import my.ym.data_articles.local.database.entities.DbEntityRelationViewedArticleAndKeyword
import my.ym.data_articles.local.database.entities.DbEntityViewedArticle

data class DbViewedArticleWithKeywords(
	@Embedded
	val viewedArticle: DbEntityViewedArticle,
	@Relation(
		parentColumn = "id", // The 'id' of the DbEntityViewedArticle entity
		entityColumn = "id", // The 'id' of the DbEntityKeyword entity
		associateBy = Junction(
			DbEntityRelationViewedArticleAndKeyword::class,
			// The column in DbRelationViewedArticleAndKeyword that references DbEntityViewedArticle's id
			parentColumn = "viewedArticleId",
			// The column in DbRelationViewedArticleAndKeyword that references DbEntityKeyword's id
			entityColumn = "keywordId",
		)
	)
	val keywords: List<DbEntityKeyword>
)
