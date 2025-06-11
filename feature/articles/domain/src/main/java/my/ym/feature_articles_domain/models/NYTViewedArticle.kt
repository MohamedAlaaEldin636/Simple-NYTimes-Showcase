package my.ym.feature_articles_domain.models

data class NYTViewedArticle(
	val title: String,
	val summary: String,

	/** Ex. Sports, Opinion */
	val section: String,
	/** Ex. By Mohamed Alaa */
	val byLine: String,

	val publishedDate: String,
	val lastUpdatedDate: String,
	val lastUpdatedTime: String,

	/** separated by Semi Colon ';' */
	val keywords: List<String>,

	val media: List<NYTMedia>,
)
