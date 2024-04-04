package com.lnight.moviecomposeapp.movie_details.di

import com.lnight.moviecomposeapp.movie_details.data.repository.ApiRepositoryImpl
import com.lnight.moviecomposeapp.movie_details.domain.repository.ApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailsApiRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindDetailsApiRepository(
        repositoryImpl: ApiRepositoryImpl
    ): ApiRepository

}