package my.ym.data_articles.repos

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import my.ym.data_articles.local.LocalDataSourceArticles
import my.ym.data_articles.models.utils.toApiDayPeriodOfMostPopularArticles
import my.ym.data_articles.models.utils.toAppSnapshotOfArticles
import my.ym.data_articles.remote.articles.RemoteDataSourceArticles
import my.ym.domain_articles.models.AppDayPeriodOfMostPopularArticles
import my.ym.domain_articles.models.AppSnapshotOfArticles
import my.ym.domain_articles.repos.RepoArticles
import my.ym.domain_shared.models.AppResult

class RepoArticlesImpl(
	private val remoteDataSourceArticles: RemoteDataSourceArticles,
	private val localDataSourceArticles: LocalDataSourceArticles,
) : RepoArticles {

	override fun getMostPopularViewedArticles(
		period: AppDayPeriodOfMostPopularArticles
	): Flow<AppResult<AppSnapshotOfArticles>> {
		return flow {
			// Loading before getting any value
			emit(AppResult.Loading())

			// Try internet firstly inshallah
			val appResult = remoteDataSourceArticles
				.getMostPopularArticlesByViewsInLastNDays(
					day = period.toApiDayPeriodOfMostPopularArticles()
				)

			val failureReason = when (appResult) {
				is AppResult.Success -> {
					localDataSourceArticles.insertAppSnapshotOfArticles(
						appResult.data.toAppSnapshotOfArticles()
					)


					null
				}
				is AppResult.Failure -> appResult.reason
			}

			emitAll(
				localDataSourceArticles.getAppSnapshotOfArticles(
					fetchFailureReason = failureReason
				).map {
					if (it == null) {
						AppResult.Failure(
							reason = AppResult.Failure.Reason.Unexpected(
								RuntimeException("Null from database")
							)
						)
					}else {
						AppResult.Success(data = it)
					}
				}
			)
		}.distinctUntilChanged()
	}

}