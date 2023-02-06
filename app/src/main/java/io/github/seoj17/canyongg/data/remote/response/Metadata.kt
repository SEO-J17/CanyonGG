package io.github.seoj17.canyongg.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Metadata(
    val dataVersion: String,
    val matchId: String,
    val participants: List<String>
)