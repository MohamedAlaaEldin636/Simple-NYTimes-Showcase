package my.ym.feature_articles_domain.useCases

import kotlinx.coroutines.flow.Flow
import my.ym.feature_articles_domain.models.NYTViewedArticle
import my.ym.feature_articles_domain.repos.RepoArticles

class GetMostPopularViewedArticlesUseCase(
	private val repoArticles: RepoArticles,
) {

	operator fun invoke(periodInDays: Int): Flow<Result<List<NYTViewedArticle>>> =
		repoArticles.getMostPopularViewedArticles(periodInDays = periodInDays)

}
