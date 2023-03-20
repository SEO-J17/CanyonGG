package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.summoner.SummonerInfoDao
import io.github.seoj17.canyongg.data.local.summoner.SummonerInfoEntity
import io.github.seoj17.canyongg.data.model.SummonerInfoDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SummonerInfoRepositoryImpl @Inject constructor(
    private val localService: SummonerInfoDao,
) : SummonerInfoRepository {
    override fun getSummonerInfo(puuid: String): Flow<SummonerInfoDataModel?> {
        return localService
            .get(puuid)
            .map {
                it?.let { entity ->
                    SummonerInfoDataModel(entity)
                }
            }
    }

    override suspend fun addSummonerInfo(entity: SummonerInfoEntity) {
        localService.insert(entity)
    }

    override suspend fun deleteSummonerInfo(puuid: String) {
        localService.delete(puuid)
    }
}
