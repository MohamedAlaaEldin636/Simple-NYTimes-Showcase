package my.ym.feature_articles_domain.useCases

import kotlinx.coroutines.flow.Flow
import my.ym.domain_shared.models.AppResult
import my.ym.feature_articles_domain.models.AppSnapshotOfArticles
import my.ym.feature_articles_domain.repos.RepoArticles

class GetMostPopularViewedArticlesUseCase(
	private val repoArticles: RepoArticles,
) {

	operator fun invoke(periodInDays: Int): Flow<AppResult<AppSnapshotOfArticles>> =
		repoArticles.getMostPopularViewedArticles(periodInDays = periodInDays)

}
