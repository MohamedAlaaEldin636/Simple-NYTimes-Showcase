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
