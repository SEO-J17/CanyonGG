package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.summoner.SummonerInfoDao
import io.github.seoj17.canyongg.data.local.summoner.SummonerInfoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SummonerInfoRepositoryImpl @Inject constructor(
    private val localService: SummonerInfoDao,
) : SummonerInfoRepository {
    override fun getSummonerInfo(puuid: String): Flow<SummonerInfoEntity?> {
        return localService.getSummonerInfo(puuid)
    }

    override suspend fun addSummonerInfo(entity: SummonerInfoEntity) {
        localService.insert(entity)
    }

    override suspend fun deleteSummonerInfo(puuid: String) {
        localService.delete(puuid)
    }
}