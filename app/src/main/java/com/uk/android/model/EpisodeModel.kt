package com.uk.android.model

data class EpisodeModel(
    override val info: Info?,
    override val results: List<EpisodeResponse>
) : BaseModel<EpisodeResponse>()

data class EpisodeResponse(
    val id: Int,
    val name: String,
    val air_date: String,
    val episode: String,
    val characters: List<String>,
    val url: String,
    val created: String
)
