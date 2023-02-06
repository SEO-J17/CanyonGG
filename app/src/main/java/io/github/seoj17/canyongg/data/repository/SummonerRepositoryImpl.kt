package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.data.model.SummonerTier
import io.github.seoj17.canyongg.data.remote.SummonerService
import javax.inject.Inject

class SummonerRepositoryImpl @Inject constructor(
    private val remoteService: SummonerService
) : SummonerRepository {
    override suspend fun getSummonerInfo(userName: String): Summoner? {
        return remoteService.getSummoner(userName)?.let {
            Summoner(it)
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