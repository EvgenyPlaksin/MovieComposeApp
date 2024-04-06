package com.lnight.moviecomposeapp.movie_details.di

import android.app.Application
import androidx.room.Room
import com.lnight.moviecomposeapp.common.Constants.BASE_URL
import com.lnight.moviecomposeapp.movie_details.data.data_source.local.MovieDetailsDao
import com.lnight.moviecomposeapp.movie_details.data.data_source.local.MovieDetailsDatabase
import com.lnight.moviecomposeapp.movie_details.data.data_source.remote.MovieDetailsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieDetailsModule {


    @Provides
    @Singleton
    fun provideMovieDetailsApi(): MovieDetailsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MovieDetailsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieDetailsDao(app: Application): MovieDetailsDao {
        return Room.databaseBuilder(
            app,
            MovieDetailsDatabase::class.java,
            MovieDetailsDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build().dao
    }
}