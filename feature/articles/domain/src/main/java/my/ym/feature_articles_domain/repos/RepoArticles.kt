package my.ym.feature_articles_domain.repos

import kotlinx.coroutines.flow.Flow
import my.ym.feature_articles_domain.models.NYTViewedArticle

interface RepoArticles {

	fun getMostPopularViewedArticles(periodInDays: Int): Flow<Result<List<NYTViewedArticle>>>

}
