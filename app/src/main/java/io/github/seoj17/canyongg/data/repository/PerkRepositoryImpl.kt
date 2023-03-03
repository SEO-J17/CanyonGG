package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.perks.PerkDao
import io.github.seoj17.canyongg.data.local.perks.PerkEntity
import io.github.seoj17.canyongg.data.model.PerkDataModel
import io.github.seoj17.canyongg.data.remote.DataCenterService
import javax.inject.Inject

class PerkRepositoryImpl @Inject constructor(
    private val dataCenterService: DataCenterService,
    private val perksDao: PerkDao,
) : PerkRepository {
    override suspend fun getPerk(id: Int): PerkEntity {
        return perksDao.get(id)
    }

    override suspend fun getPerksList(): List<PerkDataModel> {
        val list = mutableListOf<PerkDataModel>()
        dataCenterService.getPerks().forEach {
            list.add(PerkDataModel(it))
            it.slots.forEach { slot ->
                list.addAll(PerkDataModel(slot.runes))
            }
        }
        return list.toList()
    }

    override suspend fun addPerksList(entity: List<PerkEntity>) {
        perksDao.insert(entity)
    }

    override suspend fun addPerk(entity: PerkEntity) {
        perksDao.insert(entity)
    }
}