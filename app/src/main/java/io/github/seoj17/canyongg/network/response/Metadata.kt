package io.github.seoj17.canyongg.network.response

import com.squareup.moshi.Json

data class Metadata(
    val dataVersion: String,
    val matchId: String,
    val participants: List<String>
)