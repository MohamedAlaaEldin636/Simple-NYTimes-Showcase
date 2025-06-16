package my.ym.data_articles.local.database.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import my.ym.data_articles.local.database.entities.DbEntityRelationSnapshotOfViewedArticlesAndViewedArticle
import my.ym.data_articles.local.database.entities.models.DbSnapshotOfViewedArticlesWithViewedArticles

@Dao
interface DaoRelationSnapshotOfViewedArticlesAndViewedArticle {

	@Transaction
	@Query("SELECT * FROM snapshot_of_viewed_articles ORDER BY id DESC")
	fun getSnapshotOfViewedArticlesWithViewedArticles(): Flow<DbSnapshotOfViewedArticlesWithViewedArticles?>

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertOrReplace(relationSnapshotOfViewedArticlesAndViewedArticle: DbEntityRelationSnapshotOfViewedArticlesAndViewedArticle)

}
