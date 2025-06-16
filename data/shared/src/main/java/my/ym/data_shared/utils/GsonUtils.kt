package my.ym.data_shared.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.Strictness
import timber.log.Timber
import java.lang.reflect.Type

data object GsonUtils {

	/** - Default [Gson] used in the app inshallah. */
	val gson: Gson by lazy {
		GsonBuilder()
			.serializeNulls()
			.setStrictness(Strictness.LENIENT)
			.disableHtmlEscaping()
			.enableComplexMapKeySerialization()
			.create()
	}

}

inline fun <reified T> T.toJsonOrNull(
	gson: Gson = GsonUtils.gson,
	type: Type? = null,
): String? {
	return try {
		if (type == null) {
			gson.toJson(this)
		}else {
			gson.toJson(this, type)
		}
	}catch (throwable: Throwable) {
		Timber.e("Gson (To) -> ${throwable.stackTraceToString()}")

		null
	}
}

inline fun <reified T> String?.fromJsonOrNull(
	gson: Gson = GsonUtils.gson,
	type: Type? = null,
): T? {
	return try {
		if (type == null) {
			gson.fromJson(this, T::class.java)
		}else {
			gson.fromJson(this, type)
		}
	}catch (throwable: Throwable) {
		Timber.e("Gson (From) -> ${throwable.stackTraceToString()}")

		null
	}
}
