package my.ym.domain_articles.useCases

import kotlinx.coroutines.flow.Flow
import my.ym.domain_articles.models.AppViewedArticle
import my.ym.domain_articles.repos.RepoArticles
import my.ym.domain_shared.models.AppResult

class GetViewedArticleDetailsUseCase(
	private val repoArticles: RepoArticles,
) {

	operator fun invoke(id: Long): Flow<AppResult<AppViewedArticle>> =
		repoArticles.getAppViewedArticle(id = id)

}
