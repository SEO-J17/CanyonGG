package io.github.seoj17.canyongg.data.remote

import io.github.seoj17.canyongg.data.remote.response.champion.ChampResponse
import io.github.seoj17.canyongg.data.remote.response.perks.PerksResponse
import io.github.seoj17.canyongg.data.remote.response.spell.SpellResponse
import retrofit2.Call
import retrofit2.http.GET

interface DataCenterApi {
    @GET("/cdn/$CDN_VERSION/data/ko_KR/summoner.json")
    fun getSpellInfo(): Call<SpellResponse>

    @GET("/cdn/$CDN_VERSION/data/ko_KR/runesReforged.json")
    fun getPerksInfo(): Call<List<PerksResponse>>

    @GET("/cdn/$CDN_VERSION/data/ko_KR/champion.json")
    fun getChampInfo(): Call<ChampResponse>

    companion object {
        private const val CDN_VERSION = "13.3.1"
    }
}