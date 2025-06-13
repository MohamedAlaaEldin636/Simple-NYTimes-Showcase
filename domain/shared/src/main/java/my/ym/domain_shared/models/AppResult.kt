package my.ym.domain_shared.models

sealed class AppResult<T> {

	class Loading<T> : AppResult<T>()

	sealed class Immediate<T> : AppResult<T>()

	data class Success<T>(
		val data: T
	) : Immediate<T>()

	sealed class Failure<T> : Immediate<T>() {

		/** - Used in case of no internet connection */
		class NoInternetConnection<T> : Failure<T>()

		/** - Used in case of a timeout indicating slow internet connection */
		class PoorInternetConnection<T> : Failure<T>()

		data class Unexpected<T>(val throwable: Throwable? = null) : Failure<T>()

	}

}