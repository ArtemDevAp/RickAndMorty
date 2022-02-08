package com.uk.android.model

data class LocationModel(
    override val info: Info?,
    override val results: List<LocationResponse>
) : BaseModel<LocationResponse>()

data class LocationResponse(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
    val residents: List<String>,
    val url: String,
    val created: String
)
