package com.uk.android.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.uk.android.data.CharacterPagingSource
import com.uk.android.data.EpisodePagingSource
import com.uk.android.data.LocationPagingSource
import com.uk.android.model.CharacterResponse
import com.uk.android.model.EpisodeResponse
import com.uk.android.model.LocationResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickAndMortyRepository @Inject constructor(
    private val episodePagingSource: EpisodePagingSource,
    private val characterPagingSource: CharacterPagingSource,
    private val locationPagingSource: LocationPagingSource
) {
    companion object {
        private const val PAGE_SIZE = 15
    }

    fun getEpisodes(): Flow<PagingData<EpisodeResponse>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                episodePagingSource
            }
        ).flow

    fun getCharacters(): Flow<PagingData<CharacterResponse>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                characterPagingSource
            }
        ).flow


    fun getLocation(): Flow<PagingData<LocationResponse>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                locationPagingSource
            }
        ).flow

}