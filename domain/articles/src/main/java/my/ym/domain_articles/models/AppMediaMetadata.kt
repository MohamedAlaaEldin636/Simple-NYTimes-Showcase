package my.ym.domain_articles.models

data class AppMediaMetadata(
	/** Ex. if type is image in [AppMedia.type] then it is a url of image like *.png or *.jpg */
	val url: String,
)
