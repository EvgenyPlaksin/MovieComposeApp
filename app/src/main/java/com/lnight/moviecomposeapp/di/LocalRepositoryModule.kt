package com.lnight.moviecomposeapp.di

import com.lnight.moviecomposeapp.movie_list.data.repository.LocalRepositoryImpl
import com.lnight.moviecomposeapp.movie_list.domain.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLocalRepository(
        repositoryImpl: LocalRepositoryImpl
    ): LocalRepository

}