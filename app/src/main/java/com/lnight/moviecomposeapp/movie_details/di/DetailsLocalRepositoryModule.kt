package com.lnight.moviecomposeapp.movie_details.di

import com.lnight.moviecomposeapp.movie_details.data.repository.LocalRepositoryImpl
import com.lnight.moviecomposeapp.movie_details.domain.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailsLocalRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLocalDetailsRepository(
        repositoryImpl: LocalRepositoryImpl
    ): LocalRepository

}