package com.uk.android.di

import android.content.Context
import com.uk.android.error.EpisodeErrorHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ErrorHandlerModule {

    @Provides
    @ViewModelScoped
    fun provideEpisodeErrorHandler(
        @ApplicationContext context: Context
    ): EpisodeErrorHandler = EpisodeErrorHandler(context)


}