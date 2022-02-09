package com.uk.android.ui.fragment.episode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import com.uk.android.error.EpisodeErrorHandler
import com.uk.android.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class EpisodeViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository,
    private val episodeErrorHandler: EpisodeErrorHandler
) : ContainerHost<EpisodeState, EpisodeSideEffect>, ViewModel() {

    override val container: Container<EpisodeState, EpisodeSideEffect> = container(
        EpisodeState(true)
    )

    init {
        loadEpisode()
    }

    private fun loadEpisode() = intent {
        viewModelScope.launch {
            rickAndMortyRepository.getEpisodes()
                .cachedIn(viewModelScope)
                .collectLatest { episodeResponse ->
                    reduce {
                        state.copy(
                            loading = false,
                            episodeResponse = episodeResponse
                        )
                    }
                }
        }
    }

    fun episodeHandler(episodeHandler: Flow<CombinedLoadStates>) = intent {
        viewModelScope.launch {
            episodeHandler
                .collectLatest { loadState ->
                    when (val load = loadState.refresh) {
                        is LoadState.Error -> {
                            val errorText = episodeErrorHandler.handleError(load.error)
                            postSideEffect(EpisodeSideEffect.ToastError(errorText))
                        }
                        else -> {
                        }
                    }
                }
        }
    }
}

