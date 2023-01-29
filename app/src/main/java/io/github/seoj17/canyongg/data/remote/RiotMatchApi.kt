package io.github.seoj17.canyongg.data.remote

import io.github.seoj17.canyongg.data.remote.response.MatchInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RiotMatchApi {
    @GET("/lol/match/v5/matches/by-puuid/{puuid}/ids")
    fun getMatchId(
        @Path("puuid") puuid: String,
        @Query("start") start: Int,
    ): Call<List<String>>

    @GET("/lol/match/v5/matches/{matchId}")
    fun getMatchInfo(
        @Path("matchId") matchId: String
    ): Call<MatchInfoResponse>
}