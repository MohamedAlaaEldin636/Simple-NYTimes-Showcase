package my.ym.data_articles.local.database.entities.models

data class DbMedia(
	val type: Type,
	val caption: String,
	val copyright: String,
	val listOfMediaMetadata: List<DbMediaMetadata>,
) {
	enum class Type {
		Image,
		Other
	}
}
