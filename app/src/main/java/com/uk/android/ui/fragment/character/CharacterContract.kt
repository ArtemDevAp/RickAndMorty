package com.uk.android.ui.fragment.character

import androidx.paging.PagingData
import com.uk.android.model.CharacterResponse

data class CharacterState(
    val loading: Boolean = true,
    val character: PagingData<CharacterResponse>? = null
)

sealed class CharacterSideEffect {
    data class SnackError(val msg: String) : CharacterSideEffect()
}