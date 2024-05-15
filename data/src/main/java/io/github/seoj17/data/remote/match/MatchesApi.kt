package io.github.seoj17.data.remote.match

import io.github.seoj17.data.remote.response.match.MatchInfoResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MatchesApi {
    @GET("/lol/match/v5/matches/by-puuid/{puuid}/ids")
    suspend fun getMatchId(
        @Path("puuid") puuid: String,
        @Query("start") start: Int,
    ): List<String>

    @GET("/lol/match/v5/matches/{matchId}")
    suspend fun getMatchInfo(
        @Path("matchId") matchId: String,
    ): MatchInfoResponse
}
