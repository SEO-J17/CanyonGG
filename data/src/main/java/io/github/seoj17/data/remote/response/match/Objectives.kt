package io.github.seoj17.data.remote.response.match

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Objectives(
    val baron: Baron,
    val dragon: Dragon,
    val tower: Tower,
)
