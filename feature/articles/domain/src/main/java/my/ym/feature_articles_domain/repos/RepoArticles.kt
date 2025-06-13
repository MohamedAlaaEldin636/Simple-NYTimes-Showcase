package my.ym.feature_articles_domain.repos

import kotlinx.coroutines.flow.Flow
import my.ym.feature_articles_domain.models.AppResult
import my.ym.feature_articles_domain.models.AppSnapshotOfArticles

interface RepoArticles {

	fun getMostPopularViewedArticles(periodInDays: Int): Flow<AppResult<AppSnapshotOfArticles>>

}
