package com.uk.android.ui.fragment.location

import androidx.paging.PagingData
import com.uk.android.model.LocationResponse

data class LocationState(
    val loading: Boolean = true,
    val locations: PagingData<LocationResponse>? = null
)

sealed class LocationSideEffect {
    data class ErrorToast(val msg: String) : LocationSideEffect()
}