package io.github.seoj17.data.repository.champion

import io.github.seoj17.data.local.champions.ChampionsEntity
import io.github.seoj17.data.model.ChampionsDataModel
import kotlinx.coroutines.flow.Flow

interface ChampionsRepository {
    suspend fun getChampionList(): List<ChampionsDataModel>
    suspend fun getChampion(id: Int): ChampionsDataModel?
    fun getAllChampion(): Flow<List<ChampionsDataModel>>
    suspend fun addChampionList(entity: List<ChampionsEntity>)
    suspend fun addChampion(entity: ChampionsEntity)
}
