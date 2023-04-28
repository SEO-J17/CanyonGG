package io.github.seoj17.data.remote.match

import io.github.seoj17.data.remote.response.match.MatchInfoResponse
import retrofit2.await
import javax.inject.Inject

class MatchesService @Inject constructor(
    private val matchesApi: MatchesApi,
) {
    suspend fun getMatchId(puuid: String, start: Int): List<String> {
        return runCatching {
            matchesApi.getMatchId(puuid, start).await()
        }.fold(
            onSuccess = { matchesId ->
                matchesId
            },
            onFailure = {
                emptyList()
            },
        )
    }

    suspend fun getMatchInfo(matchId: String): MatchInfoResponse? {
        return runCatching {
            matchesApi.getMatchInfo(matchId).await()
        }.fold(
            onSuccess = { matchInfo ->
                matchInfo
            },
            onFailure = {
                null
            },
        )
    }
}
