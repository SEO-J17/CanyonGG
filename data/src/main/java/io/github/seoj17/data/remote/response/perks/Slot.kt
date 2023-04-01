package io.github.seoj17.data.remote.response.perks

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Slot(
    val runes: List<Rune>,
)
