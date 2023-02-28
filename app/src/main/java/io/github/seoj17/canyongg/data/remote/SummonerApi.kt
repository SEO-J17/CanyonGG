package io.github.seoj17.canyongg.data.remote

import io.github.seoj17.canyongg.data.remote.response.champion.RotationChampResponse
import io.github.seoj17.canyongg.data.remote.response.summoner.SummonerResponse
import io.github.seoj17.canyongg.data.remote.response.summoner.TierResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface SummonerApi {
    @GET("/lol/summoner/v4/summoners/by-name/{summonerName}")
    fun getSummoner(
        @Path("summonerName") summonerName: String,
    ): Call<SummonerResponse>

    @GET("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    fun getSummonerTier(
        @Path("encryptedSummonerId") summonerId: String,
    ): Call<List<TierResponse>>

    @GET("/lol/platform/v3/champion-rotations")
    fun getRotationChampIds(): Call<RotationChampResponse>
}