package my.ym.data_shared.local.database.converters

import androidx.room.TypeConverter
import my.ym.core_kotlin.toPairOfSameTypeOrNull
import my.ym.core_kotlin.toTripleOfSameTypeOrNull
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data object DbConverterOfJavaTime {

	private const val HYPHEN = "-"
	private const val COLON = ":"
	private const val SPACE = " "

	@TypeConverter
	fun fromLocalDate(value: String?): LocalDate? {
		return value?.let { value ->
			val (year, monthValue, dayOfMonth) = value.split(HYPHEN).toTripleOfSameTypeOrNull {
				it.toIntOrNull()
			} ?: return null

			LocalDate.of(year, monthValue, dayOfMonth)
		}
	}
	@TypeConverter
	fun localDateToString(value: LocalDate?): String? {
		return value?.let { date ->
			"${date.year}$HYPHEN${date.monthValue}$HYPHEN${date.dayOfMonth}"
		}
	}

	@TypeConverter
	fun fromLocalTime(value: String?): LocalTime? {
		return value?.let { value ->
			val (hour, minute, seconds) = value.split(COLON).toTripleOfSameTypeOrNull {
				it.toIntOrNull()
			} ?: return null

			LocalTime.of(hour, minute, seconds)
		}
	}
	@TypeConverter
	fun localTimeToString(value: LocalTime?): String? {
		return value?.let { time ->
			"${time.hour}$COLON${time.minute}$COLON${time.second}"
		}
	}

	@TypeConverter
	fun fromLocalDateTime(value: String?): LocalDateTime? {
		return value?.let { value ->
			val (datePart, timePart) = value.split(SPACE).toPairOfSameTypeOrNull {
				it
			} ?: return null

			val localDate = fromLocalDate(datePart) ?: return null
			val localTime = fromLocalTime(timePart) ?: return null

			LocalDateTime.of(localDate, localTime)
		}
	}
	@TypeConverter
	fun localDateTimeToString(value: LocalDateTime?): String? {
		return value?.let { dateTime ->
			val datePart = localDateToString(value = dateTime.toLocalDate())
				?: return null
			val timePart = localTimeToString(value = dateTime.toLocalTime())
				?: return null

			"$datePart$SPACE$timePart"
		}
	}

}
