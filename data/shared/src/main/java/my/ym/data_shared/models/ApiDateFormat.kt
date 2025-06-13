package my.ym.data_shared.models

import my.ym.core_kotlin.toTripleOfSameTypeOrNull
import java.time.LocalDate
import kotlin.text.isNullOrEmpty
import kotlin.text.split

@JvmInline
value class ApiDateFormat(private val dateInFormat: String?) {

	companion object {
		private const val HYPHEN = '-'
	}

	/** - Converts format of 'yyyy-mm-dd' to [LocalDate] inshallah. */
	fun toLocalDateOrNull(): LocalDate? {
		if (dateInFormat.isNullOrEmpty()) return null

		val (year, month, day) = dateInFormat.split(HYPHEN)
			.toTripleOfSameTypeOrNull { it.toIntOrNull() } ?: return null

		return LocalDate.of(year, month, day)
	}

}
