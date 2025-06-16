package my.ym.data_articles.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import my.ym.data_articles.local.database.entities.DbEntitySnapshotOfViewedArticles
import java.time.LocalDateTime

@Dao
interface DaoSnapshotOfViewedArticles {

	@Query("SELECT EXISTS(SELECT id FROM snapshot_of_viewed_articles WHERE fetched_from_api_at = :fetchedFromApiAt LIMIT 1)")
	suspend fun exists(fetchedFromApiAt: LocalDateTime): Boolean

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(snapshotOfViewedArticles: DbEntitySnapshotOfViewedArticles): Long

	@Insert(onConflict = OnConflictStrategy.ABORT)
	suspend fun insertOrAbort(snapshotOfViewedArticles: DbEntitySnapshotOfViewedArticles): Long

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(listOfSnapshotOfViewedArticles: List<DbEntitySnapshotOfViewedArticles>): List<Long>

}
