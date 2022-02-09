package com.uk.android.data

import com.uk.android.model.CharacterModel
import com.uk.android.model.EpisodeModel
import com.uk.android.model.LocationModel
import retrofit2.http.GET
import retrofit2.http.Url

interface RickiAndMortyApiService {

    @GET
    suspend fun getCharacters(@Url character: String): CharacterModel

    @GET
    suspend fun getLocations(@Url location: String): LocationModel

    @GET
    suspend fun getPagedEpisode(@Url page: String): EpisodeModel
}
