package com.uk.android.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.uk.android.model.CharacterResponse
import kotlinx.coroutines.delay
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(
    private val rickiAndMortyApiService: RickiAndMortyApiService
) : PagingSource<String, CharacterResponse>() {

    companion object {
        private const val STARTING_PAGE_URL = "https://rickandmortyapi.com/api/character/?page=1"
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, CharacterResponse> {

        val pageUrl = params.key ?: STARTING_PAGE_URL

        return try {
            val response = rickiAndMortyApiService.getCharacters(pageUrl)

            delay(400)

            LoadResult.Page(
                data = response.results,
                prevKey = response.info?.prev,
                nextKey = response.info?.next
            )

        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<String, CharacterResponse>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
                ?: state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }
}