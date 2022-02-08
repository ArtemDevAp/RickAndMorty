package com.uk.android.model

abstract class BaseModel<T>(
    open val info: Info? = null,
    open val results: List<T> = emptyList()
)

data class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)