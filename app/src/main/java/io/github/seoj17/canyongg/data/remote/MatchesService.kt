package io.github.seoj17.canyongg.data.remote

import io.github.seoj17.canyongg.data.remote.response.match.MatchInfoResponse
import retrofit2.await
import javax.inject.Inject

class MatchesService @Inject constructor(
    private val responseMatch: MatchesApi
) {
    suspend fun getMatchId(puuid: String, start: Int): List<String> {
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
}