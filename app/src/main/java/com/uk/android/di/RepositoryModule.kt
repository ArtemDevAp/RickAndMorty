package com.uk.android.di

import com.uk.android.data.CharacterPagingSource
import com.uk.android.data.EpisodePagingSource
import com.uk.android.data.LocationPagingSource
import com.uk.android.repository.RickAndMortyRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRickAndMortyRepository(
        episodePaging: EpisodePagingSource,
        locationPagingSource: LocationPagingSource,
        characterPagingSource: CharacterPagingSource
    ): RickAndMortyRepository = RickAndMortyRepository(
        episodePagingSource = episodePaging,
        locationPagingSource = locationPagingSource,
        characterPagingSource = characterPagingSource
    )

}