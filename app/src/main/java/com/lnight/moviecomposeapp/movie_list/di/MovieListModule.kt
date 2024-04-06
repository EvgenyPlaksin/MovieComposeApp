package com.lnight.moviecomposeapp.movie_list.di

import android.app.Application
import androidx.room.Room
import com.lnight.moviecomposeapp.common.Constants.BASE_URL
import com.lnight.moviecomposeapp.movie_list.data.data_source.local.MovieComposeDao
import com.lnight.moviecomposeapp.movie_list.data.data_source.local.MovieComposeDatabase
import com.lnight.moviecomposeapp.movie_list.data.data_source.remote.MovieListApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieListModule {

    @Provides
    @Singleton
    fun provideMovieListApi(): MovieListApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MovieListApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieComposeDao(app: Application): MovieComposeDao {
        return Room.databaseBuilder(
            app,
            MovieComposeDatabase::class.java,
            MovieComposeDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration().build().dao
    }
}