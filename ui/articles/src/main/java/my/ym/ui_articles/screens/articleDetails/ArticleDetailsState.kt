package my.ym.ui_articles.screens.articleDetails

import my.ym.domain_articles.models.AppViewedArticle
import my.ym.domain_shared.models.AppResult

data class ArticleDetailsState(
	val appResult: AppResult<AppViewedArticle> = AppResult.Loading(),
	val selectedImageIndex: Int = 0,
)
