package com.uk.android.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.uk.android.data.EpisodePagingSource
import com.uk.android.data.RickiAndMortyApiService
import com.uk.android.model.CharacterModel
import com.uk.android.model.EpisodeResult
import com.uk.android.model.LocationModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

const val PAGE_SIZE = 15

class RickAndMortyRepository @Inject constructor(
    private val rickiAndMortyApiService: RickiAndMortyApiService,
    private val pagingSource: EpisodePagingSource
) {

    fun getEpisodes(): Flow<PagingData<EpisodeResult>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                pagingSource
            }
        ).flow

    suspend fun getCharacters(): CharacterModel =
        rickiAndMortyApiService.getCharacters()

    suspend fun getLocation(): LocationModel =
        rickiAndMortyApiService.getLocations()

}