package my.ym.domain_shared.models

sealed class AppResult<T> {

	class Loading<T> : AppResult<T>()

	sealed class Immediate<T> : AppResult<T>()

	data class Success<T>(
		val data: T
	) : Immediate<T>()

	data class Failure<T>(val reason: Reason) : Immediate<T>() {

		companion object;

		/**
		 * ###### Can be 1 of the following
		 *
		 * - [NoInternetConnection]
		 * - [PoorInternetConnection]
		 * - [Unexpected]
		 */
		sealed interface Reason {
			/** - Used in case of no internet connection */
			data object NoInternetConnection : Reason

			/** - Used in case of a timeout indicating slow internet connection */
			data object PoorInternetConnection : Reason

			data class Unexpected(val throwable: Throwable? = null) : Reason
		}

	}

}