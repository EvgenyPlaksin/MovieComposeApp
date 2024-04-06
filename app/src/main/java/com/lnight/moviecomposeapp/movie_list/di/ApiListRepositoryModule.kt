package com.lnight.moviecomposeapp.movie_list.di

import com.lnight.moviecomposeapp.movie_list.data.repository.ApiRepositoryImpl
import com.lnight.moviecomposeapp.movie_list.domain.repository.ApiRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ApiListRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindApiListRepository(
        repositoryImpl: ApiRepositoryImpl
    ): ApiRepository

}