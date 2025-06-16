package my.ym.ui_articles.screens.mostPopularArticles

sealed interface MostPopularArticlesIntent {

	data object RetryToFetchData : MostPopularArticlesIntent

	data object PullToRefresh : MostPopularArticlesIntent

	data object HideMessageAtBottom : MostPopularArticlesIntent

	data class ChangeVisibilityOfInfoTopSheet(val show: Boolean) : MostPopularArticlesIntent

	companion object {
		fun showInfoTopSheet() = ChangeVisibilityOfInfoTopSheet(show = true)

		fun hideInfoTopSheet() = ChangeVisibilityOfInfoTopSheet(show = false)
	}

}
