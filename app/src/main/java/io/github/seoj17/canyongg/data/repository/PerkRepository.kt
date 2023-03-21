package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.perks.PerkEntity
import io.github.seoj17.canyongg.data.model.PerkDataModel

interface PerkRepository {
    suspend fun getPerk(id: Int): PerkEntity
    suspend fun getPerksList(): List<PerkDataModel>
    suspend fun addPerksList(entity: List<PerkEntity>)
    suspend fun addPerk(entity: PerkEntity)
}
