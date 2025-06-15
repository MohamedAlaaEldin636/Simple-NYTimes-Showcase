package my.ym.data_articles.remote.articles

import my.ym.data_articles.models.ApiSnapshotOfArticles
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceArticles {

	@GET("/mostpopular/v2/viewed/{lastNDays}.json")
	suspend fun getMostPopularArticlesByViewsInLastNDays(
		@Path("lastNDays") lastNDays: Int,
	): Response<ApiSnapshotOfArticles?>

}
