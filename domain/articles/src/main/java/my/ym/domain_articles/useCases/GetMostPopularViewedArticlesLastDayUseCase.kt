package my.ym.domain_articles.useCases

import kotlinx.coroutines.flow.Flow
import my.ym.domain_articles.models.AppDayPeriodOfMostPopularArticles
import my.ym.domain_shared.models.AppResult
import my.ym.domain_articles.models.AppSnapshotOfArticles
import my.ym.domain_articles.repos.RepoArticles

class GetMostPopularViewedArticlesLastDayUseCase /*@Inject */constructor(
	private val repoArticles: RepoArticles,
) {

	operator fun invoke(): Flow<AppResult<AppSnapshotOfArticles>> =
		repoArticles.getMostPopularViewedArticles(
			period = AppDayPeriodOfMostPopularArticles.LastDay
		)

}
