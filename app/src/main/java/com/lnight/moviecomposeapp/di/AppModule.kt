package com.lnight.moviecomposeapp.di

import android.app.Application
import androidx.room.Room
import com.lnight.moviecomposeapp.movie_list.data.data_sourse.local.MovieComposeDao
import com.lnight.moviecomposeapp.movie_list.data.data_sourse.local.MovieComposeDatabase
import com.lnight.moviecomposeapp.movie_list.data.data_sourse.remote.MoviesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val BASE_URL = "https://api.themoviedb.org/3/"

    @Provides
    @Singleton
    fun provideMoviesApi(): MoviesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MoviesApi::class.java)
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