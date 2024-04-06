package com.lnight.moviecomposeapp.movie_details.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lnight.moviecomposeapp.movie_details.domain.model.DetailsConverters
import com.lnight.moviecomposeapp.movie_details.domain.model.MovieDetails

@Database(
    entities = [MovieDetails::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(DetailsConverters::class)
abstract class MovieDetailsDatabase: RoomDatabase() {

    abstract val dao: MovieDetailsDao

    companion object {
        const val DATABASE_NAME = "movie_details_db"
    }
}