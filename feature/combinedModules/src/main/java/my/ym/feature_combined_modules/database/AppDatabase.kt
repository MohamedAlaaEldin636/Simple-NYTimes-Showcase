package my.ym.feature_combined_modules.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import my.ym.data_articles.local.database.BaseDatabaseOfArticles
import my.ym.data_articles.local.database.converters.DbConverterOfListOfDbMedia
import my.ym.data_articles.local.database.entities.DbEntityKeyword
import my.ym.data_articles.local.database.entities.DbEntityRelationSnapshotOfViewedArticlesAndViewedArticle
import my.ym.data_articles.local.database.entities.DbEntityRelationViewedArticleAndKeyword
import my.ym.data_articles.local.database.entities.DbEntitySnapshotOfViewedArticles
import my.ym.data_articles.local.database.entities.DbEntityViewedArticle
import my.ym.data_shared.local.database.converters.DbConverterOfJavaTime

@Database(
	entities = [
		DbEntityViewedArticle::class,

		DbEntityKeyword::class,
		DbEntityRelationViewedArticleAndKeyword::class,

		DbEntitySnapshotOfViewedArticles::class,
		DbEntityRelationSnapshotOfViewedArticlesAndViewedArticle::class,
	],
	version = 1
)
@TypeConverters(
	DbConverterOfJavaTime::class,

	DbConverterOfListOfDbMedia::class,
)
abstract class AppDatabase : RoomDatabase(), BaseDatabaseOfArticles
