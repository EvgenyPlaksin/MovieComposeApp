package com.lnight.moviecomposeapp.movie_details.di

import com.lnight.moviecomposeapp.common.Constants.BASE_URL
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
}