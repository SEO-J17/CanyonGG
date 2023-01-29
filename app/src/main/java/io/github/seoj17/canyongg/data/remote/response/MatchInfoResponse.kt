package io.github.seoj17.canyongg.data.remote.response

import com.squareup.moshi.Json

data class MatchInfoResponse(
    @Json(name = "info")
    val info: Info,
    @Json(name = "metadata")
    val metadata: Metadata
)