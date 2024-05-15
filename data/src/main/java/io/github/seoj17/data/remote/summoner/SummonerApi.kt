package io.github.seoj17.data.remote.summoner

import io.github.seoj17.data.remote.response.champion.RotationChampResponse
import io.github.seoj17.data.remote.response.summoner.SummonerResponse
import io.github.seoj17.data.remote.response.summoner.TierResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SummonerApi {
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    suspend fun getSummoner(
        @Path("summonerName") summonerName: String,
    ): SummonerResponse

    @GET("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    suspend fun getSummonerTier(
        @Path("encryptedSummonerId") summonerId: String,
    ): List<TierResponse>

    @GET("/lol/platform/v3/champion-rotations")
    suspend fun getRotationChampIds(): RotationChampResponse
}
