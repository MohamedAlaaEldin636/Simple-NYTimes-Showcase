package my.ym.data_articles.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import my.ym.data_articles.local.database.entities.DbEntityKeyword

@Dao
interface DaoKeyword {

	@Query("SELECT id FROM keyword WHERE keyword = :keyword")
	suspend fun getIdOrNull(keyword: String): Long?

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(keyword: DbEntityKeyword): Long

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(keywords: List<DbEntityKeyword>): List<Long>

}
