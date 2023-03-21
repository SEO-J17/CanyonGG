package io.github.seoj17.canyongg.data.remote.response.match

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Perks(
    val statPerks: StatPerks,
    val styles: List<Style>,
)
