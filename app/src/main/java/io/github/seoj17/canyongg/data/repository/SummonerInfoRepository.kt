package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.SummonerInfoEntity
import kotlinx.coroutines.flow.Flow

interface SummonerInfoRepository {
    fun getSummonerInfo(puuid: String): Flow<SummonerInfoEntity?>
    suspend fun addSummonerInfo(entity: SummonerInfoEntity)
    suspend fun deleteSummonerInfo(puuid: String)
}