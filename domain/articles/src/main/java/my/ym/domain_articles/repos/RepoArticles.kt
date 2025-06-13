package my.ym.domain_articles.repos

import kotlinx.coroutines.flow.Flow
import my.ym.domain_shared.models.AppResult
import my.ym.domain_articles.models.AppSnapshotOfArticles

interface RepoArticles {

	fun getMostPopularViewedArticles(periodInDays: Int): Flow<AppResult<AppSnapshotOfArticles>>

}
