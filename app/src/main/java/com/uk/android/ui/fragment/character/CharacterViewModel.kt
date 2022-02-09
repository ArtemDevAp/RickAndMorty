package com.uk.android.ui.fragment.character

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
class CharacterViewModel @Inject constructor(
    private val rickAndMortyRepository: RickAndMortyRepository
) : ContainerHost<CharacterState, CharacterSideEffect>, ViewModel() {

    override val container: Container<CharacterState, CharacterSideEffect> =
        container(CharacterState(true))

    init {
        loadCharacter()
    }

    private fun loadCharacter() = intent {
        viewModelScope.launch {
            rickAndMortyRepository.getCharacters()
                .cachedIn(viewModelScope)
                .collectLatest { character ->
                    reduce {
                        state.copy(loading = false, character = character)
                    }
                }
        }

    }

}