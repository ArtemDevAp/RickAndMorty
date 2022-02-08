package com.uk.android.di

import com.uk.android.data.EpisodePagingSource
import com.uk.android.data.RickiAndMortyApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PaginationSourceModule {

    @Singleton
    @Provides
    fun provideEpisodePagingSource(
        rickAndMortyApiService: RickiAndMortyApiService
    ): EpisodePagingSource = EpisodePagingSource(rickAndMortyApiService)

}