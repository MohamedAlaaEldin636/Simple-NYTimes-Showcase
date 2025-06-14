package my.ym.ui_shared.utils

import my.ym.domain_shared.models.AppResult
import android.content.Context
import my.ym.ui_shared.R

fun AppResult.Failure.Reason.getLocalizedText(context: Context): String {
	return when (this) {
		AppResult.Failure.Reason.NoInternetConnection -> context.getString(R.string.no_internet_connection)
		AppResult.Failure.Reason.PoorInternetConnection -> context.getString(R.string.slow_internet_connection)
		is AppResult.Failure.Reason.Unexpected -> context.getString(R.string.unexpected_error)
	}
}
