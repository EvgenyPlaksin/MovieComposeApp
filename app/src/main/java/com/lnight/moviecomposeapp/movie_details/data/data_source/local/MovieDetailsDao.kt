package com.lnight.moviecomposeapp.movie_details.data.data_source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lnight.moviecomposeapp.movie_details.domain.model.MovieDetails

@Dao
interface MovieDetailsDao {

    @Query("SELECT * FROM moviedetails WHERE :id = id")
    suspend fun getMovieDetailsById(id: Int): MovieDetails

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieDetails(movieDetails: MovieDetails)
}