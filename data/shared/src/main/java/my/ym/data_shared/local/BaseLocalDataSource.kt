package my.ym.data_shared.local

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber

abstract class BaseLocalDataSource {

	protected suspend inline fun <reified T : Any> safeDbCall(
		crossinline fetch: suspend () -> T?
	): T? {
		return withContext(Dispatchers.IO) {
			try {
				fetch()
			}catch (throwable: Throwable) {
				Timber.e("BaseLocalDataSource -> ${throwable.stackTraceToString()}")

				null
			}
		}
	}

}
