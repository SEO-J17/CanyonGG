package io.github.seoj17.data.repository.summoner

import io.github.seoj17.data.local.summoner.SummonerInfoDao
import io.github.seoj17.data.local.summoner.SummonerInfoEntity
import io.github.seoj17.data.model.SummonerInfoDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SummonerInfoRepositoryImpl @Inject constructor(
    private val summonerInfoDao: SummonerInfoDao,
) : SummonerInfoRepository {
    override fun getSummonerInfo(puuid: String): Flow<SummonerInfoDataModel?> {
        return summonerInfoDao
            .get(puuid)
            .map {
                it?.let { entity ->
                    SummonerInfoDataModel(entity)
                }
            }
    }

    override suspend fun addSummonerInfo(entity: SummonerInfoEntity) {
        summonerInfoDao.insert(entity)
    }

    override suspend fun deleteSummonerInfo(puuid: String) {
        summonerInfoDao.delete(puuid)
    }
}
