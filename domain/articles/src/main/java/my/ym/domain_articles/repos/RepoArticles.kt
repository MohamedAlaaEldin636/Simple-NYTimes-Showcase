package my.ym.domain_articles.repos

import kotlinx.coroutines.flow.Flow
import my.ym.domain_articles.models.AppDayPeriodOfMostPopularArticles
import my.ym.domain_shared.models.AppResult
import my.ym.domain_articles.models.AppSnapshotOfArticles
import my.ym.domain_articles.models.AppViewedArticle

interface RepoArticles {

	fun getMostPopularViewedArticles(
		period: AppDayPeriodOfMostPopularArticles
	): Flow<AppResult<AppSnapshotOfArticles>>

	fun getAppViewedArticle(id: Long): Flow<AppResult<AppViewedArticle>>

}
