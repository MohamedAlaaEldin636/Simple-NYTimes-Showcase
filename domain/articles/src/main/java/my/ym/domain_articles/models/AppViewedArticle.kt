package my.ym.domain_articles.models

import java.time.LocalDate
import java.time.LocalDateTime

data class AppViewedArticle(
	val id: Long,

	val title: String,
	val summary: String,

	/** Ex. Sports, Opinion */
	val section: String,
	/** Ex. By Mohamed Alaa */
	val byLine: String,

	val publishedAt: LocalDate?,
	val lastUpdatedAt: LocalDateTime?,

	val keywords: List<String>,

	val listOfMedia: List<AppMedia>,
)
