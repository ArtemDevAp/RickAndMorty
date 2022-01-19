package com.uk.android.data

import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
class Episodes(
    val info: Info? = null,
    val results: List<Result> = emptyList()
)

@JsonClass(generateAdapter = true)
class Info(
    val count: Int,
    val pages: Int,
    val next: String?,
    val prev: String?
)

@JsonClass(generateAdapter = true)
class Result(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)
