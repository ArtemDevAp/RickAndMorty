package com.uk.android.ui.fragment.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.uk.android.repository.RickAndMortyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) : ContainerHost<LocationState, LocationSideEffect>, ViewModel() {
    override val container: Container<LocationState, LocationSideEffect> = container(
        LocationState(true)
    )

    init {
        loadLocation()
    }

    private fun loadLocation() = intent {
        viewModelScope.launch {
            rickAndMortyRepository.getLocation()
                .cachedIn(viewModelScope)
                .collectLatest { locations ->
                    reduce {
                        state.copy(loading = false, locations = locations)
                    }
                }
        }
    }

}