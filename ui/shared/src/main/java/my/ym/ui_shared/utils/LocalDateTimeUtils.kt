package my.ym.ui_shared.utils

import androidx.compose.ui.text.intl.Locale
import java.time.LocalDate
import java.time.format.TextStyle

fun LocalDate.getLocalizedText(): String {
	val monthText = month.getDisplayName(
		TextStyle.FULL,
		java.util.Locale(Locale.current.language.ifEmpty { java.util.Locale.ENGLISH.language })
	)

	return "$dayOfMonth $monthText $year"
}
