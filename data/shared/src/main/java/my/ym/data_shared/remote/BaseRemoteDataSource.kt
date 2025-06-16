package my.ym.data_shared.remote

import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import my.ym.domain_shared.models.AppResult
import retrofit2.Response
import timber.log.Timber
import java.io.IOException

abstract class BaseRemoteDataSource {

	protected suspend inline fun <reified T : Any> safeApiCall(
		crossinline fetch: suspend () -> Response<T?>
	): AppResult.Immediate<T> {
		return withContext(context = Dispatchers.IO) {
			try {
				val response = fetch()

				if (response.isSuccessful) {
					val data = response.body()

					if (data == null) {
						AppResult.Failure(
							reason = AppResult.Failure.Reason.IncorrectData
						)
					}else {
						AppResult.Success(
							data = data
						)
					}
				}else {
					val reason = AppResult.Failure.Reason.getFromErrorCodeOrNull(
						code = response.code()
					)

					if (reason != null) {
						AppResult.Failure(reason = reason)
					}else {
						AppResult.Failure(
							reason = AppResult.Failure.Reason.Unexpected(
								throwable = RuntimeException(response.message().orEmpty())
							)
						)
					}
				}
			}catch (ioException: IOException) {
				/*when (ioException) {
					is SocketTimeoutException -> AppResult.Failure.Reason.PoorInternetConnection
					is SSLException -> AppResult.Failure.Reason.SecurityIssue
					is UnknownHostException,// -> AppResult.Failure.Reason.ServerDown
					is ConnectException,// -> AppResult.Failure.Reason.PoorInternetConnection
					is SocketException -> AppResult.Failure.Reason.MightBeNoInternetConnection
					else -> TO-DO()
				}*/
				Timber.e(ioException.stackTraceToString())

				AppResult.Failure(
					reason = AppResult.Failure.Reason.PoorInternetConnection
				)
			}catch (jsonSyntaxException: JsonSyntaxException) {
				Timber.e(jsonSyntaxException.stackTraceToString())

				AppResult.Failure(
					reason = AppResult.Failure.Reason.IssueInDecodingData
				)
			}catch (throwable: Throwable) {
				Timber.e(throwable.stackTraceToString())

				AppResult.Failure(
					reason = AppResult.Failure.Reason.Unexpected(throwable = throwable)
				)
			}
		}
	}

}
