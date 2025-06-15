package my.ym.data_articles.remote.articles

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
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

			// todo insert into room and get from room is better inshallah
			emit(
				remoteDataSourceArticles.getMostPopularArticlesByViewsInLastNDays(
					day = period.toApiDayPeriodOfMostPopularArticles()
				).map {
					it.toAppSnapshotOfArticles()
				}
			)

			//emitAll(anotherFlowSourceWhichIsRoomInshallah) // todo ensure it works isa.
		}.distinctUntilChanged()
		// todo to ensure changes correctly isa. or if from backend then use immediately kda ya3ne isa.
			/*databaseOne.stateIn()
			.map {  }*/
	}

}
