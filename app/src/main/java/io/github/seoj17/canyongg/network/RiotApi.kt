package io.github.seoj17.canyongg.network

import io.github.seoj17.canyongg.network.response.Info
import io.github.seoj17.canyongg.network.response.MatchInfoResponse
import io.github.seoj17.canyongg.network.response.SummonerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RiotApi {
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    fun getSummoner(
        @Path("summonerName") summonerName: String
    ): Call<SummonerResponse>

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