package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.recent.search.RecentSearchDao
import io.github.seoj17.canyongg.data.local.recent.search.RecentSearchNameEntity
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.data.model.SummonerTier
import io.github.seoj17.canyongg.data.remote.SummonerService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SummonerRepositoryImpl @Inject constructor(
    private val remoteService: SummonerService,
    private val localService: RecentSearchDao,
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

    override suspend fun addRecentSummoner(puuid: String, name: String) {
        return localService.insert(RecentSearchNameEntity(puuid, name))
    }

    override fun getRecentSummoner(): Flow<List<RecentSearchNameEntity>> {
        return localService.getSummoners()
    }

    override suspend fun getRotationChamps(): List<Int> {
        return remoteService.getRotationChamps()
    }

    override suspend fun deleteRecentSummoner(name: String) {
        return localService.delete(name)
    }

    override suspend fun deleteAllRecentSummoners() {
        return localService.deleteAll()
    }
}