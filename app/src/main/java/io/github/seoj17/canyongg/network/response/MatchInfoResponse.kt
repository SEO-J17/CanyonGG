package io.github.seoj17.canyongg.network.response

import com.squareup.moshi.Json

data class MatchInfoResponse(
    @Json(name = "info")
    val info: Info,
    @Json(name = "metadata")
    val metadata: Metadata
)