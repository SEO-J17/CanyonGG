package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.champions.ChampionsEntity
import io.github.seoj17.canyongg.data.model.ChampionsDataModel

interface ChampionsRepository {
    suspend fun getChampionList(): List<ChampionsDataModel>
    suspend fun getChampion(id: Int): String
    suspend fun addChampionList(entity: List<ChampionsEntity>)
    suspend fun addChampion(entity: ChampionsEntity)
}
