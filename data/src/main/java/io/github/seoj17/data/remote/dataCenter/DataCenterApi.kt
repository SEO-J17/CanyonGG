package io.github.seoj17.data.remote.dataCenter

import io.github.seoj17.data.remote.response.champion.ChampResponse
import io.github.seoj17.data.remote.response.perks.PerksResponse
import io.github.seoj17.data.remote.response.spell.SpellResponse
import retrofit2.Call
import retrofit2.http.GET

interface DataCenterApi {
    @GET("data/ko_KR/summoner.json")
    fun getSpellInfo(): Call<SpellResponse>

    @GET("data/ko_KR/runesReforged.json")
    fun getPerksInfo(): Call<List<PerksResponse>>

    @GET("data/ko_KR/champion.json")
    fun getChampInfo(): Call<ChampResponse>
}
