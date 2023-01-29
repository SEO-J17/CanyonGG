package io.github.seoj17.canyongg.data.remote

import io.github.seoj17.canyongg.data.remote.response.MatchInfoResponse
import io.github.seoj17.canyongg.data.remote.response.SummonerResponse
import io.github.seoj17.canyongg.data.remote.response.TierResponse
import retrofit2.await
import javax.inject.Inject

class ResponseService @Inject constructor(
    private val responseSummoner: RiotApi,
    private val responseMatch: RiotMatchApi
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

    suspend fun getMatchId(puuid: String, start: Int = 0): List<String> {
        return runCatching {
            responseMatch.getMatchId(puuid, start).await()
        }.fold(
            onSuccess = { matchesId ->
                matchesId
            },
            onFailure = {
                emptyList()
            }
        )
    }

    suspend fun getMatchInfo(matchId: String): MatchInfoResponse {
        return runCatching {
            responseMatch.getMatchInfo(matchId).await()
        }.fold(
            onSuccess = { matchInfo ->
                matchInfo
            }, onFailure = {
                throw it
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