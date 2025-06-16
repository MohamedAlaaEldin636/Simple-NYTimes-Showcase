package my.ym.feature_combined_modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import my.ym.data_articles.local.database.daos.DaoKeyword
import my.ym.data_articles.local.database.daos.DaoRelationSnapshotOfViewedArticlesAndViewedArticle
import my.ym.data_articles.local.database.daos.DaoRelationViewedArticleAndKeyword
import my.ym.data_articles.local.database.daos.DaoSnapshotOfViewedArticles
import my.ym.data_articles.local.database.daos.DaoViewedArticle
import my.ym.feature_combined_modules.database.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
data object HiltModuleOfDatabase {

	@Provides
	@Singleton
	fun provideAppDatabase(@ApplicationContext applicationContext: Context): AppDatabase {
		return Room.databaseBuilder(
			applicationContext,
			AppDatabase::class.java,
			"app-database"
		).build()
	}

	@Provides
	fun provideDaoKeyword(appDatabase: AppDatabase) : DaoKeyword {
		return appDatabase.daoKeyword()
	}

	@Provides
	fun provideDaoViewedArticle(appDatabase: AppDatabase) : DaoViewedArticle {
		return appDatabase.daoViewedArticle()
	}

	@Provides
	fun provideDaoSnapshotOfViewedArticles(appDatabase: AppDatabase) : DaoSnapshotOfViewedArticles {
		return appDatabase.daoSnapshotOfViewedArticles()
	}

	@Provides
	fun provideDaoRelationViewedArticleAndKeyword(appDatabase: AppDatabase) : DaoRelationViewedArticleAndKeyword {
		return appDatabase.daoRelationViewedArticleAndKeyword()
	}

	@Provides
	fun provideDaoRelationSnapshotOfViewedArticlesAndViewedArticle(appDatabase: AppDatabase) : DaoRelationSnapshotOfViewedArticlesAndViewedArticle {
		return appDatabase.daoRelationSnapshotOfViewedArticlesAndViewedArticle()
	}

}