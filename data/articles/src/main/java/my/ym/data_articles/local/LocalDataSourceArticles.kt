package my.ym.data_articles.local

import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import my.ym.data_articles.local.database.daos.DaoKeyword
import my.ym.data_articles.local.database.daos.DaoRelationSnapshotOfViewedArticlesAndViewedArticle
import my.ym.data_articles.local.database.daos.DaoRelationViewedArticleAndKeyword
import my.ym.data_articles.local.database.daos.DaoSnapshotOfViewedArticles
import my.ym.data_articles.local.database.daos.DaoViewedArticle
import my.ym.data_articles.local.database.entities.DbEntityKeyword
import my.ym.data_articles.local.database.entities.DbEntityRelationSnapshotOfViewedArticlesAndViewedArticle
import my.ym.data_articles.local.database.entities.DbEntityRelationViewedArticleAndKeyword
import my.ym.data_articles.models.utils.toAppSnapshotOfArticles
import my.ym.data_articles.models.utils.toAppViewedArticle
import my.ym.data_articles.models.utils.toDbEntitySnapshotOfViewedArticles
import my.ym.data_articles.models.utils.toDbEntityViewedArticle
import my.ym.data_shared.local.BaseLocalDataSource
import my.ym.domain_articles.models.AppSnapshotOfArticles
import my.ym.domain_articles.models.AppViewedArticle
import my.ym.domain_shared.models.AppResult
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSourceArticles @Inject constructor(
	private val daoKeyword: DaoKeyword,
	private val daoViewedArticle: DaoViewedArticle,
	private val daoSnapshotOfViewedArticles: DaoSnapshotOfViewedArticles,
	private val daoRelationViewedArticleAndKeyword: DaoRelationViewedArticleAndKeyword,
	private val daoRelationSnapshotOfViewedArticlesAndViewedArticle: DaoRelationSnapshotOfViewedArticlesAndViewedArticle,
) : BaseLocalDataSource() {

	fun getAppViewedArticle(id: Long): Flow<AppViewedArticle?> {
		return daoRelationViewedArticleAndKeyword.getViewedArticleWithKeywordsAsFlow(
			id = id
		).map { dbViewedArticleWithKeywords ->
			dbViewedArticleWithKeywords?.toAppViewedArticle()
		}
	}

	fun getAppSnapshotOfArticles(
		fetchFailureReason: AppResult.Failure.Reason?,
	): Flow<AppSnapshotOfArticles?> {
		return daoRelationSnapshotOfViewedArticlesAndViewedArticle
			.getSnapshotOfViewedArticlesWithViewedArticles().map {
				it?.toAppSnapshotOfArticles(
					fetchFailureReason = fetchFailureReason,
					getKeywords = { article ->
						val keywords = daoRelationViewedArticleAndKeyword
							.getViewedArticleWithKeywords(article.id)?.keywords ?: return@map null

						keywords.map { it.keyword }
					},
				)
			}
	}

	@Transaction
	suspend fun insertAppSnapshotOfArticles(
		data: AppSnapshotOfArticles,
	) {
		val snapshotOfViewedArticles = data
			.toDbEntitySnapshotOfViewedArticles()

		if (daoSnapshotOfViewedArticles.exists(snapshotOfViewedArticles.fetchedFromApiAt)) {
			return
		}

		val snapshotOfViewedArticlesId = daoSnapshotOfViewedArticles
			.insertOrReplace(snapshotOfViewedArticles)

		for (article in data.articles) {
			if (daoViewedArticle.exists(article.id).not()) {
				daoViewedArticle.insertOrReplace(article.toDbEntityViewedArticle())

				for (keyword in article.keywords) {
					val keywordId = daoKeyword.getIdOrNull(keyword = keyword)
						?: daoKeyword.insertOrReplace(DbEntityKeyword(keyword = keyword))

					daoRelationViewedArticleAndKeyword.insertOrReplace(
						relationViewedArticleAndKeyword = DbEntityRelationViewedArticleAndKeyword(
							viewedArticleId = article.id,
							keywordId = keywordId,
						)
					)
				}
			}

			daoRelationSnapshotOfViewedArticlesAndViewedArticle.insertOrReplace(
				relationSnapshotOfViewedArticlesAndViewedArticle = DbEntityRelationSnapshotOfViewedArticlesAndViewedArticle(
					snapshotOfViewedArticlesId = snapshotOfViewedArticlesId,
					viewedArticleId = article.id
				)
			)
		}
	}

}
