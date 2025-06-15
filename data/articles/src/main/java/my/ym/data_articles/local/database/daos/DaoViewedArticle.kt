package my.ym.data_articles.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import my.ym.data_articles.local.database.entities.DbEntityViewedArticle

@Dao
interface DaoViewedArticle {

	@Query("SELECT EXISTS(SELECT id FROM viewed_articles WHERE id = :id LIMIT 1)")
	suspend fun exists(id: Long): Boolean

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(viewedArticle: DbEntityViewedArticle)

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(viewedArticles: List<DbEntityViewedArticle>)

}
