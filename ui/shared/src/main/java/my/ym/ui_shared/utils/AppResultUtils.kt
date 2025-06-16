package my.ym.ui_shared.utils

import my.ym.domain_shared.models.AppResult
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import my.ym.ui_shared.R

fun AppResult.Failure.Reason.getLocalizedText(context: Context): String {
	return when (this) {
		AppResult.Failure.Reason.NoInternetConnection -> context.getString(R.string.no_internet_connection)
		AppResult.Failure.Reason.PoorInternetConnection -> context.getString(R.string.slow_internet_connection)
		AppResult.Failure.Reason.IncorrectData -> context.getString(R.string.incorrect_data_received_from_internet)
		AppResult.Failure.Reason.IssueInDecodingData -> context.getString(R.string.issue_in_decoding_the_data)
		AppResult.Failure.Reason.TooManyRequests -> context.getString(R.string.too_many_requests_please_wait_sometime_before_retry)
		AppResult.Failure.Reason.UnauthorizedRequest -> context.getString(R.string.unauthorized_request)
		is AppResult.Failure.Reason.Unexpected -> context.getString(R.string.unexpected_error)
	}
}

@Composable
fun AppResult.Failure.Reason.getLocalizedText(): String {
	val context = LocalContext.current

	return when (this) {
		AppResult.Failure.Reason.NoInternetConnection -> context.getString(R.string.no_internet_connection)
		AppResult.Failure.Reason.PoorInternetConnection -> context.getString(R.string.slow_internet_connection)
		AppResult.Failure.Reason.IncorrectData -> context.getString(R.string.incorrect_data_received_from_internet)
		AppResult.Failure.Reason.IssueInDecodingData -> context.getString(R.string.issue_in_decoding_the_data)
		AppResult.Failure.Reason.TooManyRequests -> context.getString(R.string.too_many_requests_please_wait_sometime_before_retry)
		AppResult.Failure.Reason.UnauthorizedRequest -> context.getString(R.string.unauthorized_request)
		is AppResult.Failure.Reason.Unexpected -> context.getString(R.string.unexpected_error)
	}
}
