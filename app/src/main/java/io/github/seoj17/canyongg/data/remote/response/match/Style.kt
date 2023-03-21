package io.github.seoj17.canyongg.data.remote.response.match

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Style(
    val description: String,
    val selections: List<Selection>,
    val style: Int,
)
