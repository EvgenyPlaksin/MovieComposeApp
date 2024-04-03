package com.lnight.moviecomposeapp.movie_list.data.data_sourse.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lnight.moviecomposeapp.movie_list.domain.model.MovieList

@Dao
interface MovieComposeDao {

    @Query("SELECT * FROM MovieList")
    suspend fun getAllMovies(): MovieList

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieList: MovieList)
}