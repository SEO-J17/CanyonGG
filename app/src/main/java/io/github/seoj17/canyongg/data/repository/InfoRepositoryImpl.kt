package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.model.MatchInfo
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.data.model.SummonerTier
import io.github.seoj17.canyongg.data.remote.ResponseService
import javax.inject.Inject

class InfoRepositoryImpl @Inject constructor(
    private val remoteService: ResponseService
) : InfoRepository {
    override suspend fun getSummonerInfo(userName: String): Summoner? {
        return remoteService.getSummoner(userName)?.let {
            Summoner(it)
        }
    }

    override suspend fun getMatchInfo(puuid: String, startIndex: Int): List<MatchInfo> {
        val matchIds = remoteService.getMatchId(puuid, startIndex)
        return matchIds.map { id ->
            MatchInfo(remoteService.getMatchInfo(id))
        }
    }

    override suspend fun getTier(id: String): SummonerTier? {
        val tierList = remoteService.getUserTier(id)
        return if (tierList.isNotEmpty()) {
            SummonerTier(tierList[0])
        } else {
            null
        }
    }
}