package io.github.seoj17.data.remote.response.summoner

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SummonerResponse(
    @Json(name = "id")
    val id: String,
    @Json(name = "accountId")
    val accountId: String,
    @Json(name = "puuid")
    val puuid: String,
    @Json(name = "name")
    val name: String,
    @Json(name = "profileIconId")
    val profileIconId: Int,
    @Json(name = "revisionDate")
    val revisionDate: Long,
    @Json(name = "summonerLevel")
    val summonerLevel: Int,
)
