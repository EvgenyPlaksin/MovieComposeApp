package com.lnight.moviecomposeapp.movie_list.domain

import com.lnight.moviecomposeapp.common.Resource

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key) -> Resource<Item>,
    private inline val getNextKey: suspend (Item) -> Key,
    private inline val onError: suspend (Resource<Item>) -> Unit,
    private inline val onSuccess: suspend (result: Resource<Item>, newKey: Key) -> Unit
): Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems() {
        if(isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey)
        isMakingRequest = false
        if(result.data == null) {
            onError(result)
            onLoadUpdated(false)
            return
        }
        currentKey = getNextKey(result.data)
        onSuccess(result, currentKey)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
    }
}