package my.ym.data_articles.remote.articles

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import my.ym.data_articles.models.utils.toApiDayPeriodOfMostPopularArticles
import my.ym.data_articles.models.utils.toAppSnapshotOfArticles
import my.ym.domain_articles.models.AppDayPeriodOfMostPopularArticles
import my.ym.domain_articles.models.AppSnapshotOfArticles
import my.ym.domain_articles.repos.RepoArticles
import my.ym.domain_shared.models.AppResult
import my.ym.domain_shared.utils.map
import kotlin.time.Duration.Companion.seconds

class RepoArticlesImpl(
	private val remoteDataSourceArticles: RemoteDataSourceArticles
) : RepoArticles {

	override fun getMostPopularViewedArticles(
		period: AppDayPeriodOfMostPopularArticles
	): Flow<AppResult<AppSnapshotOfArticles>> {
		// todo temp till make local data source as well isa.
		return flow {
			emit(AppResult.Loading())

			delay(3.seconds)

			emit(
				remoteDataSourceArticles.getMostPopularArticlesByViewsInLastNDays(
					day = period.toApiDayPeriodOfMostPopularArticles()
				).map {
					it.toAppSnapshotOfArticles()
				}
			)
		}
	}

}
