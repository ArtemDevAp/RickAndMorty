package com.uk.android.error

import android.content.Context
import com.uk.android.R
import com.uk.android.util.NoConnectionInterceptor
import java.net.UnknownHostException
import javax.inject.Inject

class EpisodeErrorHandler @Inject constructor(
    private val context: Context
) {

    fun handleError(throwable: Throwable): String {
        return when (throwable) {
            is NoConnectionInterceptor.NoInternetException -> context.getString(R.string.unknown_host)
            else -> context.getString(R.string.unknown_error)
        }
    }

}