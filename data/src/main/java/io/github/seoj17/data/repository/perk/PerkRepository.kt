package io.github.seoj17.data.repository.perk

import io.github.seoj17.data.local.perks.PerkEntity
import io.github.seoj17.data.model.PerkDataModel

interface PerkRepository {
    suspend fun getPerk(id: Int): PerkEntity
    suspend fun getPerksList(): List<PerkDataModel>
    suspend fun addPerksList(entity: List<PerkEntity>)
    suspend fun addPerk(entity: PerkEntity)
}
