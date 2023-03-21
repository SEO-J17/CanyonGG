package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.summoner.SummonerInfoEntity
import io.github.seoj17.canyongg.data.model.SummonerInfoDataModel
import kotlinx.coroutines.flow.Flow

interface SummonerInfoRepository {
    fun getSummonerInfo(puuid: String): Flow<SummonerInfoDataModel?>
    suspend fun addSummonerInfo(entity: SummonerInfoEntity)
    suspend fun deleteSummonerInfo(puuid: String)
}
