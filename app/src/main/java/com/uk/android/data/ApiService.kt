package com.uk.android.data

import retrofit2.http.GET

interface ApiService {

    @GET("episode/")
    suspend fun getEpisodes(): Episodes
}
