package io.github.seoj17.data.repository.summoner

import io.github.seoj17.data.local.recent.RecentSearchDao
import io.github.seoj17.data.local.recent.RecentSearchNameEntity
import io.github.seoj17.data.model.RecentSearchNameDataModel
import io.github.seoj17.data.model.SummonerDataModel
import io.github.seoj17.data.model.SummonerTierDataModel
import io.github.seoj17.data.remote.summoner.SummonerService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SummonerRepositoryImpl @Inject constructor(
    private val remoteService: SummonerService,
    private val localService: RecentSearchDao,
) : SummonerRepository {
    override suspend fun getSummonerInfo(userName: String): SummonerDataModel? {
        return remoteService
            .getSummoner(userName)
            ?.let {
                SummonerDataModel(it)
            }
    }

    override suspend fun getTier(id: String): SummonerTierDataModel? {
        val tierList = remoteService.getUserTier(id)
        return if (tierList.isNotEmpty()) {
            SummonerTierDataModel(tierList[0])
        } else {
            null
        }
    }

    override suspend fun addRecentSummoner(puuid: String, name: String) {
        return localService.insert(RecentSearchNameEntity(puuid, name))
    }

    override fun getRecentSummoner(): Flow<List<RecentSearchNameDataModel>> {
        return localService
            .get()
            .map {
                RecentSearchNameDataModel(it)
            }
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
