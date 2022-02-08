package com.uk.android.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.uk.android.model.EpisodeResult
import javax.inject.Inject

class EpisodePagingSource @Inject constructor(
    private val rickiAndMortyApiService: RickiAndMortyApiService
) : PagingSource<String, EpisodeResult>() {

    companion object {
        private const val STARTING_PAGE_INDEX = "https://rickandmortyapi.com/api/episode?page=1"
    }

    override suspend fun load(params: LoadParams<String>): LoadResult<String, EpisodeResult> {
        val pageIndex = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = rickiAndMortyApiService.getPagedEpisode(pageIndex)

            LoadResult.Page(
                data = response.results,
                prevKey = if (response.info == null) null else response.info.prev,
                nextKey = if (response.info == null) null else response.info.next
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<String, EpisodeResult>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
                ?: state.closestPageToPosition(anchorPosition)?.nextKey
        }
    }

}