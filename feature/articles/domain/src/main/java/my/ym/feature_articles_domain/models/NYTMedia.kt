package my.ym.feature_articles_domain.models

data class NYTMedia(
	val caption: String,
	val copyright: String,
	val mediaMetadata: List<NYTMediaMetadata>,
)
