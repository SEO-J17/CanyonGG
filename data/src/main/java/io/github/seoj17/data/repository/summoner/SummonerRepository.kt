package io.github.seoj17.data.repository.summoner

import io.github.seoj17.data.model.RecentSearchNameDataModel
import io.github.seoj17.data.model.SummonerDataModel
import io.github.seoj17.data.model.SummonerTierDataModel
import kotlinx.coroutines.flow.Flow

interface SummonerRepository {
    suspend fun getSummonerInfo(userName: String): Flow<SummonerDataModel?>
    suspend fun getTier(id: String): SummonerTierDataModel?
    fun getRecentSummoner(): Flow<List<RecentSearchNameDataModel>>
    suspend fun getRotationChamps(): List<Int>
    suspend fun addRecentSummoner(puuid: String, name: String)
    suspend fun deleteRecentSummoner(name: String)
    suspend fun deleteAllRecentSummoners()
}
