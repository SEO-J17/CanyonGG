package io.github.seoj17.data.remote.summoner

import io.github.seoj17.data.remote.response.summoner.SummonerResponse
import io.github.seoj17.data.remote.response.summoner.TierResponse
import retrofit2.await
import javax.inject.Inject

class SummonerService @Inject constructor(
    private val summonerApi: SummonerApi,
) {
    suspend fun getSummoner(name: String): SummonerResponse? {
        return runCatching {
            summonerApi.getSummoner(name).await()
        }.fold(
            onSuccess = { summonerInfo ->
                summonerInfo
            },
            onFailure = {
                null
            },
        )
    }

    suspend fun getUserTier(id: String): List<TierResponse> {
        return runCatching {
            summonerApi.getSummonerTier(id).await()
        }.fold(
            onSuccess = { tierList ->
                tierList
            },
            onFailure = {
                emptyList()
            },
        )
    }

    suspend fun getRotationChamps(): List<Int> {
        return runCatching {
            summonerApi.getRotationChampIds().await()
        }.fold(
            onSuccess = { response ->
                response.freeChampionIds
            },
            onFailure = {
                emptyList()
            },
        )
    }
}
