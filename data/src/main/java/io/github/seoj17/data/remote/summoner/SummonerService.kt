package io.github.seoj17.data.remote.summoner

import io.github.seoj17.data.remote.response.summoner.SummonerResponse
import io.github.seoj17.data.remote.response.summoner.TierResponse
import javax.inject.Inject

class SummonerService @Inject constructor(
    private val summonerApi: SummonerApi,
) {
    suspend fun getSummoner(name: String): SummonerResponse = summonerApi.getSummoner(name)
    
    suspend fun getUserTier(id: String): List<TierResponse> = summonerApi.getSummonerTier(id)
    
    suspend fun getRotationChamps(): List<Int> = summonerApi.getRotationChampIds().freeChampionIds
}
