package my.ym.data_articles.models

import com.google.gson.annotations.SerializedName

data class ApiViewedArticle(
	val id: Long?,

	val title: String?,
	@SerializedName("abstract")
	val summary: String?,

	/** Ex. Sports, Opinion */
	val section: String?,
	/** Ex. By Mohamed Alaa */
	@SerializedName("byline")
	val byLine: String?,

	/** Ex. 2025-06-12 */
	@SerializedName("published_date")
	val publishedDate: String?,
	/** Ex. 2025-06-13 04:42:43 */
	@SerializedName("updated")
	val lastUpdatedDateAndTime: String?,

	/** separated by Semi Colon ';' */
	@SerializedName("adx_keywords")
	val keywords: String?,

	val media: List<ApiMedia>?,
)
