package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.ChampionsEntity
import io.github.seoj17.canyongg.data.model.DataChampions

interface ChampionsRepository {
    suspend fun getChampionList(): List<DataChampions>
    suspend fun getChampion(id: Int): String
    suspend fun addChampionList(entity: List<ChampionsEntity>)
    suspend fun addChampion(entity: ChampionsEntity)
}