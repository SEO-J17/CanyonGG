package io.github.seoj17.canyongg.network

import io.github.seoj17.canyongg.network.response.MatchInfoResponse
import io.github.seoj17.canyongg.network.response.SummonerResponse
import retrofit2.await
import javax.inject.Inject

class ResponseService @Inject constructor(
    private val responseApi: RiotApi
) {
    suspend fun getSummoner(name: String = "롤코타이쿤3"): SummonerResponse {
        return runCatching {
            responseApi.getSummoner(name).await()
        }.fold(
            onSuccess = { summonerInfo ->
                summonerInfo
            }, onFailure = {
                throw it
            }
        )
    }

    suspend fun getMatchId(puuid: String, start: Int = 0): List<String> {
        return runCatching {
            responseApi.getMatchId(puuid, start).await()
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
            responseApi.getMatchInfo(matchId).await()
        }.fold(
            onSuccess = { matchInfo ->
                matchInfo
            }, onFailure = {
                throw it
            }
        )
    }
}