package com.lnight.moviecomposeapp.movie_details.domain.model

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDate

class DetailsConverters {
    private val json = Json { ignoreUnknownKeys = true }

    @TypeConverter
    fun fromJsonCompany(jsonString: String): List<Company> {
        return json.decodeFromString(jsonString)
    }

    @TypeConverter
    fun toJsonCompany(movies: List<Company>): String {
        return json.encodeToString(movies)
    }

    @TypeConverter
    fun fromJsonGenres(jsonString: String): List<String> {
        return json.decodeFromString(jsonString)
    }

    @TypeConverter
    fun toJsonGenres(genres: List<String>): String {
        return json.encodeToString(genres)
    }

    @TypeConverter
    fun fromStringReleaseDate(dateString: String): LocalDate {
        return LocalDate.parse(dateString)
    }

    @TypeConverter
    fun toStringReleaseDate(releaseDate: LocalDate): String {
        return releaseDate.toString()
    }
}
