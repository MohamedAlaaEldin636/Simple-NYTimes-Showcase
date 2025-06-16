package my.ym.ui_shared.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

fun LocalDate.getLocalizedText(): String {
	return format(DateTimeFormatter.ofPattern("d MMM yyyy"))
}

fun LocalTime.getLocalizedText(): String {
	return format(DateTimeFormatter.ofPattern("hh:mm a"))
}

fun LocalDateTime.getLocalizedText(): String {
	return "${toLocalDate().getLocalizedText()} ${toLocalTime().getLocalizedText()}"
}
