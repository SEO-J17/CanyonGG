package io.github.seoj17.canyongg.data.remote.response.champion

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ChampData(
    val blurb: String,
    val id: String,
    val info: ChampInfo,
    val key: String,
    val name: String,
    val partype: String,
    val title: String,
    val version: String,
)