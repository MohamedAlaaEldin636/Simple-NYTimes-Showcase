package my.ym.feature_articles_domain.models

data class AppMedia(
	val type: Type,
	val caption: String,
	val copyright: String,
	val mediaMetadata: List<AppMediaMetadata>,
) {
	enum class Type {
		Image,
		Other
	}
}
