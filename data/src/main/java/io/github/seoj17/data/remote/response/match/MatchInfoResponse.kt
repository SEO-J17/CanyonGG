package io.github.seoj17.data.remote.response.match

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MatchInfoResponse(
    @Json(name = "info")
    val info: Info,
    @Json(name = "metadata")
    val metadata: Metadata,
)
