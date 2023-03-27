package io.github.seoj17.data.repository

import io.github.seoj17.data.local.champions.ChampionsEntity
import io.github.seoj17.data.model.ChampionsDataModel

interface ChampionsRepository {
    suspend fun getChampionList(): List<ChampionsDataModel>
    suspend fun getChampion(id: Int): String
    suspend fun addChampionList(entity: List<ChampionsEntity>)
    suspend fun addChampion(entity: ChampionsEntity)
}
