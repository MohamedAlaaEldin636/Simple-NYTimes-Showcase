package my.ym.data_shared.models

import my.ym.core_kotlin.toPairOfSameTypeOrNull
import java.time.LocalDateTime
import kotlin.text.isNullOrEmpty
import kotlin.text.split

@JvmInline
value class ApiDateAndTimeFormat(private val dateAndTimeInFormat: String?) {

	companion object {
		private const val SPACE = ' '
	}

	/** - Converts format of 'yyyy-mm-dd hh-mm-ss' to [LocalDateTime] inshallah. */
	fun toLocalDateTimeOrNull(): LocalDateTime? {
		if (dateAndTimeInFormat.isNullOrEmpty()) return null

		val (datePart, timePart) = dateAndTimeInFormat.split(SPACE)
			.toPairOfSameTypeOrNull { it } ?: return null

		return LocalDateTime.of(
			ApiDateFormat(datePart).toLocalDateOrNull() ?: return null,
			ApiTimeFormat(timePart).toLocalTimeOrNull() ?: return null,
		)
	}

}
