package my.ym.ui_articles.screens.mostPopularArticles

import my.ym.domain_articles.models.AppSnapshotOfArticles

/**
 * ### Case 1 -> Start of Screen at first time
 * - Just show progress bar loading data inshallah.
 *
 * ### Case 2 -> Error fetching data + no cache
 * - show error at center of screen via [Data.Error] inshallah
 * - also compose button of 'Retry' in center below error inshallah.
 *
 * ### Case 3-A -> Error fetching data + has cache (< 1 day fresh)
 * - show [messageAtTop] Updated 4 hours ago + stopwatch like to update it inshallah
 * - show [messageAtBottom] Data might be outdated, as it was updated since 11:20 PM, you can pull
 * to refresh to refresh data if you have good internet connection inshallah, with action OK.
 * - show [Data.Success]
 *
 * ### Case 3-B -> Error fetching data + has cache (>= 1 day fresh)
 * - show [Data.Error] of why couldn't fetch from rest api
 * - show [messageAtBottom] Data cache is too old, so you have to refresh inshallah (ok).
 * - also compose button of 'Retry' in center below error inshallah.
 *
 * ### Case 4 -> Success fetching data
 * - show [messageAtTop] Updated just now + stopwatch like to update it inshallah
 * - Optional show [messageAtBottom] if was shown before (if was true), to say, Data is up to date
 * and bg is green unlike warning yellow or red error ones inshallah, auto remove after snackbar
 * min duration inshallah.
 * - show [Data.Success]
 */
data class MostPopularArticlesState(
	val loadingStatus: LoadingStatus = LoadingStatus.Other,
	val data: Data? = null,

	val messageAtTop: String? = null,
	val messageAtBottom: MessageWithType? = null,
	val previousMessageAtBottom: MessageWithType? = null,

	val showInfoTopSheet: Boolean = false,

	// todo.
	//val showFavorites: Boolean,
	//val showHistory: Boolean,
) {

	val isLoading: Boolean get() = loadingStatus == LoadingStatus.Other
			|| loadingStatus == LoadingStatus.PullingToRefresh

	sealed interface Data {

		data class Success(val snapshotOfArticles: AppSnapshotOfArticles) : Data

		data class Error(val msg: String) : Data

	}

	/**
	 * ### Represents 1 of the following
	 *
	 * - [None] -> no loading is happening
	 * - [PullingToRefresh] -> loading due to pull to refresh
	 * - [Other] -> loading due to anything else other than pull to refresh
	 *
	 * ### Why
	 *
	 * - To reflect different emit of UI to represent the loading inshallah.
	 */
	enum class LoadingStatus {
		None, PullingToRefresh, Other
	}

	enum class MessageType {
		Normal, Success, Warning, Error
	}

	data class MessageWithType(
		val message: String,
		val type: MessageType,
	)

}
