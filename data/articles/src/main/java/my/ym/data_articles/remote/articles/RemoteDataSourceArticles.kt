package my.ym.data_articles.remote.articles

import my.ym.data_articles.models.ApiDayPeriodOfMostPopularArticles
import my.ym.data_shared.remote.BaseRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RemoteDataSourceArticles @Inject constructor(
	private val apiServiceArticles: ApiServiceArticles,
) : BaseRemoteDataSource() {

	suspend fun getMostPopularArticlesByViewsInLastNDays(
		day: ApiDayPeriodOfMostPopularArticles,
	) = safeApiCall {
		apiServiceArticles.getMostPopularArticlesByViewsInLastNDays(lastNDays = day.numOfDays)
	}

}
