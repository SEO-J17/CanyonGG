package io.github.seoj17.data.remote.response.spell

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpellImage(
    val full: String,
    val group: String,
    val h: Int,
    val sprite: String,
    val w: Int,
    val x: Int,
    val y: Int,
)
