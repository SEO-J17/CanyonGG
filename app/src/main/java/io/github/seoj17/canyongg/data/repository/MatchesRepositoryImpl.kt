package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.model.MatchInfo
import io.github.seoj17.canyongg.data.remote.MatchesService
import javax.inject.Inject

class MatchesRepositoryImpl @Inject constructor(
    private val remoteService: MatchesService
) : MatchesRepository {
    override suspend fun getMatchInfo(puuid: String, startIndex: Int): List<MatchInfo> {
        val matchIds = remoteService.getMatchId(puuid, startIndex)
        return matchIds.map { id ->
            MatchInfo(remoteService.getMatchInfo(id))
        }
    }
}