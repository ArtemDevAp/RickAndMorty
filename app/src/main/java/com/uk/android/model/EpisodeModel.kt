package com.uk.android.model

data class EpisodeModel(
    override val info: Info?,
    override val results: List<EpisodeResult>
) : BaseModel<EpisodeResult>()

data class EpisodeResult(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)
