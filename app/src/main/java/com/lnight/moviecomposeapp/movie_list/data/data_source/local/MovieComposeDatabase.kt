package com.lnight.moviecomposeapp.movie_list.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.lnight.moviecomposeapp.movie_list.domain.model.Converters
import com.lnight.moviecomposeapp.movie_list.domain.model.MovieList

@Database(
    entities = [MovieList::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class MovieComposeDatabase: RoomDatabase() {

    abstract val dao: MovieComposeDao

    companion object {
        const val DATABASE_NAME = "movies_db"
    }
}