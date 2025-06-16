package my.ym.ui_articles.screens.articleDetails

sealed interface ArticleDetailsIntent {

	data class SelectImage(val index: Int) : ArticleDetailsIntent

	data object RetryToFetchData : ArticleDetailsIntent

	companion object {

		fun moveToNextImage(currentIndex: Int, imagesSize: Int): SelectImage {
			return SelectImage(
				index = if (currentIndex == imagesSize.dec()) 0 else currentIndex.inc()
			)
		}

	}

}
