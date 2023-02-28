package io.github.seoj17.canyongg.data.remote.response.spell

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpellInfo(
    val description: String,
    val id: String,
    val image: SpellImage,
    val key: String,
    val modes: List<String>,
    val name: String,
    val tooltip: String,
    val vars: List<Any>,
)