package io.github.seoj17.canyongg.data.remote

import io.github.seoj17.canyongg.contract.UrlContract
import io.github.seoj17.canyongg.data.remote.response.champion.ChampResponse
import io.github.seoj17.canyongg.data.remote.response.perks.PerksResponse
import io.github.seoj17.canyongg.data.remote.response.spell.SpellResponse
import retrofit2.Call
import retrofit2.http.GET

interface DataCenterApi {
    @GET("/cdn/${UrlContract.CDN_VERSION}/data/ko_KR/summoner.json")
    fun getSpellInfo(): Call<SpellResponse>

    @GET("/cdn/${UrlContract.CDN_VERSION}/data/ko_KR/runesReforged.json")
    fun getPerksInfo(): Call<List<PerksResponse>>

    @GET("/cdn/${UrlContract.CDN_VERSION}/data/ko_KR/champion.json")
    fun getChampInfo(): Call<ChampResponse>
}