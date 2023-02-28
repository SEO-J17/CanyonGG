package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.recent.search.RecentSearchNameEntity
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.data.model.SummonerTier
import kotlinx.coroutines.flow.Flow

interface SummonerRepository {
    suspend fun getSummonerInfo(userName: String): Summoner?
    suspend fun getTier(id: String): SummonerTier?
    fun getRecentSummoner(): Flow<List<RecentSearchNameEntity>>
    suspend fun getRotationChamps(): List<Int>
    suspend fun addRecentSummoner(puuid: String, name: String)
    suspend fun deleteRecentSummoner(name: String)
    suspend fun deleteAllRecentSummoners()
}