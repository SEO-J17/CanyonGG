package io.github.seoj17.data.remote.match

import io.github.seoj17.data.remote.response.match.MatchInfoResponse
import javax.inject.Inject

class MatchesService @Inject constructor(
    private val matchesApi: MatchesApi,
) {
    suspend fun getMatchId(puuid: String, start: Int): List<String> =
        matchesApi.getMatchId(puuid, start)
    
    suspend fun getMatchInfo(matchId: String): MatchInfoResponse =
        matchesApi.getMatchInfo(matchId)
}
