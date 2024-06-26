package com.lnight.moviecomposeapp.movie_list.di

import com.lnight.moviecomposeapp.movie_list.data.repository.LocalRepositoryImpl
import com.lnight.moviecomposeapp.movie_list.domain.repository.LocalRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalListRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLocalListRepository(
        repositoryImpl: LocalRepositoryImpl
    ): LocalRepository

}