package com.example.jetpack_dictionary.data.local.entity

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.jetpack_dictionary.core.util.json.JsonParser
import com.example.jetpack_dictionary.data.domain.model.Meaning
import com.google.gson.reflect.TypeToken


@ProvidedTypeConverter // We need to provide our own
class Converters(private val jsonParser: JsonParser) {

    @TypeConverter
    fun fromMeaningsJson(json: String): List<Meaning> {
        return jsonParser.fromJson<ArrayList<Meaning>>(
            json,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toMeaningsJson(meanings: List<Meaning>): String {
        return jsonParser.toJson(
            meanings,
            object : TypeToken<ArrayList<Meaning>>() {}.type
        ) ?: "[]"
    }
}