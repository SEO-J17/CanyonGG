package io.github.seoj17.data.remote.dataCenter

import io.github.seoj17.data.remote.response.champion.ChampResponse
import io.github.seoj17.data.remote.response.perks.PerksResponse
import io.github.seoj17.data.remote.response.spell.SpellResponse
import retrofit2.http.GET

interface DataCenterApi {
    @GET("data/ko_KR/summoner.json")
    suspend fun getSpellInfo(): SpellResponse

    @GET("data/ko_KR/runesReforged.json")
    suspend fun getPerksInfo(): List<PerksResponse>

    @GET("data/ko_KR/champion.json")
    suspend fun getChampInfo(): ChampResponse
}
