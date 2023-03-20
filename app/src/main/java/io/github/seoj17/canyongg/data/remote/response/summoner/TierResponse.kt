package io.github.seoj17.canyongg.data.remote.response.summoner

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TierResponse(
    @Json(name = "freshBlood")
    val freshBlood: Boolean,

    @Json(name = "hotStreak")
    val hotStreak: Boolean,

    @Json(name = "inactive")
    val inactive: Boolean,

    @Json(name = "leagueId")
    val leagueId: String,

    @Json(name = "leaguePoints")
    val leaguePoints: Int,

    @Json(name = "losses")
    val losses: Int,

    @Json(name = "queueType")
    val queueType: String,

    @Json(name = "rank")
    val rank: String,

    @Json(name = "summonerId")
    val summonerId: String,

    @Json(name = "summonerName")
    val summonerName: String,

    @Json(name = "tier")
    val tier: String,

    @Json(name = "veteran")
    val veteran: Boolean,

    @Json(name = "wins")
    val wins: Int,
)
