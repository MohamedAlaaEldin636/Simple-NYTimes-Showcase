package my.ym.feature_articles_domain.models

sealed class AppResult<T> {

	data object Loading : AppResult<Nothing>()

	sealed class Immediate<T> : AppResult<T>()

	data class Success<T>(
		val data: T
	) : Immediate<T>()

	sealed class Failure : Immediate<Nothing>() {

		/** - Used in case of no internet connection */
		data object NoInternetConnection : Failure()

		/** - Used in case of a timeout indicating slow internet connection */
		data object PoorInternetConnection : Failure()

		data class Unexpected(val throwable: Throwable? = null) : Failure()

	}

}
