package com.lnight.moviecomposeapp.movie_list.domain.model

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class Converters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromJson(jsonString: String): Set<Movie> {
        return json.decodeFromString(jsonString)
    }

    @TypeConverter
    fun toJson(movies: Set<Movie>): String {
        return json.encodeToString(movies)
    }
}
