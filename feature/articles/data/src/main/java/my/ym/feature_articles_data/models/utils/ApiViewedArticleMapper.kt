package my.ym.feature_articles_data.models.utils

import my.ym.feature_articles_data.models.ApiViewedArticle
import my.ym.feature_articles_domain.models.AppViewedArticle
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

private const val SEMI_COLON = ';'
private const val HYPHEN = '-'
private const val SPACE = ' '
private const val COLON = ':'

fun ApiViewedArticle.toAppViewedArticle(): AppViewedArticle {
	return AppViewedArticle(
		title = title.orEmpty(),
		summary = summary.orEmpty(),
		section = section.orEmpty(),
		byLine = byLine.orEmpty(),
		publishedAt = publishedDate.convertToLocalDate(),
		lastUpdatedAt = lastUpdatedDateAndTime.convertToLocalDateTime(),
		keywords = keywords.orEmpty().split(SEMI_COLON),
		media = media.orEmpty().map { it.toAppMedia() }
	)
}

/** Used to convert [ApiViewedArticle.publishedDate] inshallah */
private fun String?.convertToLocalDate(): LocalDate? {
	// Ex. 2025-06-12
	if (isNullOrEmpty()) return null

	val (year, month, day) = split(HYPHEN)
		.toTripleOfSameTypeOrNull { it.toIntOrNull() } ?: return null

	return LocalDate.of(year, month, day)
}

/** Used to convert [ApiViewedArticle.lastUpdatedDateAndTime] inshallah */
private fun String?.convertToLocalDateTime(): LocalDateTime? {
	// Ex. 2025-06-13 04:42:43
	if (isNullOrEmpty()) return null

	val (datePart, timePart) = split(SPACE)
		.toPairOfSameTypeOrNull { it } ?: return null

	val (year, month, day) = datePart.split(HYPHEN)
		.toTripleOfSameTypeOrNull { it.toIntOrNull() } ?: return null

	val (hour, minute, seconds) = timePart.split(COLON)
		.toTripleOfSameTypeOrNull { it.toIntOrNull() } ?: return null

	return LocalDateTime.of(
		LocalDate.of(year, month, day),
		LocalTime.of(hour, minute, seconds)
	)
}

private inline fun <reified T, R> List<T>.toTripleOfSameTypeOrNull(
	conversion: (T) -> R?,
): Triple<R, R, R>? {
	var index = 0
	return Triple(
		conversion(getOrNull(index = index++) ?: return null) ?: return null,
		conversion(getOrNull(index = index++) ?: return null) ?: return null,
		conversion(getOrNull(index = index++) ?: return null) ?: return null,
	)
}

private inline fun <reified T, R> List<T>.toPairOfSameTypeOrNull(
	conversion: (T) -> R?,
): Pair<R, R>? {
	var index = 0
	return Pair(
		conversion(getOrNull(index = index++) ?: return null) ?: return null,
		conversion(getOrNull(index = index++) ?: return null) ?: return null,
	)
}
