package io.github.seoj17.canyongg.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Objectives(
    val baron: Baron,
    val champion: Champion,
    val dragon: Dragon,
    val inhibitor: Inhibitor,
    val riftHerald: RiftHerald,
    val tower: Tower
)