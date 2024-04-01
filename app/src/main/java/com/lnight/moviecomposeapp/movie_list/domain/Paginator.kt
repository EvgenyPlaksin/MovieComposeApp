package com.lnight.moviecomposeapp.movie_list.domain

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}