package my.ym.domain_shared.utils

import my.ym.domain_shared.models.AppResult
import my.ym.domain_shared.models.AppResult.Failure.Reason

/** @return failure with [Reason.NoInternetConnection] */
fun <T> AppResult.Failure.Companion.noInternetConnection() =
	AppResult.Failure<T>(reason = Reason.NoInternetConnection)

/** @return failure with [Reason.PoorInternetConnection] */
fun <T> AppResult.Failure.Companion.poorInternetConnection() =
	AppResult.Failure<T>(reason = Reason.PoorInternetConnection)

/** @return failure with [Reason.Unexpected] */
fun <T> AppResult.Failure.Companion.unexpected(throwable: Throwable? = null) =
	AppResult.Failure<T>(reason = Reason.Unexpected(throwable))

fun <From, To> AppResult<From>.map(conversionOfSuccess: (From) -> To): AppResult<To> {
	return when (this) {
		is AppResult.Loading -> AppResult.Loading()
		is AppResult.Failure -> AppResult.Failure(reason = reason)
		is AppResult.Success -> AppResult.Success(data = conversionOfSuccess(data))
	}
}
