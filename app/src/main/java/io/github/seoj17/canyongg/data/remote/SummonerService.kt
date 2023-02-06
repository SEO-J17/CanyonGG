package io.github.seoj17.canyongg.data.remote

import io.github.seoj17.canyongg.data.remote.response.SummonerResponse
import io.github.seoj17.canyongg.data.remote.response.TierResponse
import retrofit2.await
import javax.inject.Inject

class SummonerService @Inject constructor(
    private val responseSummoner: SummonerApi,
) {
    suspend fun getSummoner(name: String): SummonerResponse? {
        return runCatching {
            responseSummoner.getSummoner(name).await()
        }.fold(
            onSuccess = { summonerInfo ->
                summonerInfo
            }, onFailure = {
                null
            }
        )
    }

    suspend fun getUserTier(id: String): List<TierResponse> {
        return runCatching {
            responseSummoner.getSummonerTier(id).await()
        }.fold(
            onSuccess = { tierList ->
                tierList
            }, onFailure = {
                emptyList()
            }
        )
    }
}