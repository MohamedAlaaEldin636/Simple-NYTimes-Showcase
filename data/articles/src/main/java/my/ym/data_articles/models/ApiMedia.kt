package my.ym.data_articles.models

import com.google.gson.annotations.SerializedName

private const val API_IMAGE_TYPE = "image"

data class ApiMedia(
	private val type: String?,
	val caption: String?,
	val copyright: String?,
	@SerializedName("media-metadata")
	val listOfMediaMetadata: List<ApiMediaMetadata>?,
) {
	val isImage get() = type == API_IMAGE_TYPE
}
