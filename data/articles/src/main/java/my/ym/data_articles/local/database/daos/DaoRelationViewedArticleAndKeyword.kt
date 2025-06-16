package my.ym.data_articles.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import my.ym.data_articles.local.database.entities.DbEntityRelationViewedArticleAndKeyword
import my.ym.data_articles.local.database.entities.models.DbViewedArticleWithKeywords

@Dao
interface DaoRelationViewedArticleAndKeyword {

	@Transaction
	@Query("SELECT * FROM viewed_articles WHERE id = :id")
	suspend fun getViewedArticleWithKeywords(id: Long): DbViewedArticleWithKeywords?

	@Transaction
	@Query("SELECT * FROM viewed_articles WHERE id = :id")
	fun getViewedArticleWithKeywordsAsFlow(id: Long): Flow<DbViewedArticleWithKeywords?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(relationViewedArticleAndKeyword: DbEntityRelationViewedArticleAndKeyword)

}
