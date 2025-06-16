package my.ym.data_articles.local.database.converters

import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import my.ym.data_articles.local.database.entities.models.DbMedia
import my.ym.data_shared.utils.fromJsonOrNull
import my.ym.data_shared.utils.toJsonOrNull

data object DbConverterOfListOfDbMedia {

	private val type = object : TypeToken<List<DbMedia>>() {}.type

	@TypeConverter
	fun fromListOfDbMedia(value: String?): List<DbMedia>? {
		return value.fromJsonOrNull(type = type)
	}

	@TypeConverter
	fun listOfDbMediaToString(value: List<DbMedia>?): String? {
		return value.toJsonOrNull(type = type)
	}

}