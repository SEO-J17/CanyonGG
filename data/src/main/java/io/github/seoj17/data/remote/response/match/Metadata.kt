package io.github.seoj17.data.remote.response.match

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Metadata(
    val dataVersion: String,
    val matchId: String,
    val participants: List<String>,
)
