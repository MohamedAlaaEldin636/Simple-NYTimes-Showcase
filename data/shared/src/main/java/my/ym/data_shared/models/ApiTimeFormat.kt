package my.ym.data_shared.models

import my.ym.core_kotlin.toTripleOfSameTypeOrNull
import java.time.LocalTime
import kotlin.text.isNullOrEmpty
import kotlin.text.split

@JvmInline
value class ApiTimeFormat(private val timeInFormat: String?) {

	companion object {
		private const val COLON = ':'
	}

	/** - Converts format of 'hh-mm-ss' to [LocalTime] inshallah. */
	fun toLocalTimeOrNull(): LocalTime? {
		if (timeInFormat.isNullOrEmpty()) return null

		val (hour, minute, seconds) = timeInFormat.split(COLON)
			.toTripleOfSameTypeOrNull { it.toIntOrNull() } ?: return null

		return LocalTime.of(hour, minute, seconds)
	}

}
