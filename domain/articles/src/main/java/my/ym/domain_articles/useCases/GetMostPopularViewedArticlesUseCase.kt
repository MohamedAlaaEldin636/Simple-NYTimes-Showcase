package my.ym.domain_articles.useCases

import kotlinx.coroutines.flow.Flow
import my.ym.domain_articles.models.AppDayPeriodOfMostPopularArticles
import my.ym.domain_shared.models.AppResult
import my.ym.domain_articles.models.AppSnapshotOfArticles
import my.ym.domain_articles.repos.RepoArticles

/** Used for testing */
internal class GetMostPopularViewedArticlesUseCase(
	private val repoArticles: RepoArticles,
) {

	operator fun invoke(periodInDays: Int): Flow<AppResult<AppSnapshotOfArticles>> =
		repoArticles.getMostPopularViewedArticles(
			period = when (periodInDays) {
				1 -> AppDayPeriodOfMostPopularArticles.LastDay
				7 -> AppDayPeriodOfMostPopularArticles.LastWeek
				else -> AppDayPeriodOfMostPopularArticles.LastMonth
			}
		)

}
