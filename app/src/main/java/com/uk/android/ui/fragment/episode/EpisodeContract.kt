package com.uk.android.ui.fragment.episode

import androidx.paging.PagingData
import com.uk.android.model.EpisodeResponse

data class EpisodeState(
    val loading: Boolean = true,
    val episodeResponse: PagingData<EpisodeResponse>? = null
)

sealed class EpisodeSideEffect {
    data class ToastError(val msg: String) : EpisodeSideEffect()
}
