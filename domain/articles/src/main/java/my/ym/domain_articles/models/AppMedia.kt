package my.ym.domain_articles.models

data class AppMedia(
	val type: Type,
	val caption: String,
	val copyright: String,
	val listOfMediaMetadata: List<AppMediaMetadata>,
) {
	enum class Type {
		Image,
		Other
	}
}
