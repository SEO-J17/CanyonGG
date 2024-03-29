package io.github.seoj17.data.repository.perk

import io.github.seoj17.data.local.perks.PerkDao
import io.github.seoj17.data.local.perks.PerkEntity
import io.github.seoj17.data.model.PerkDataModel
import io.github.seoj17.data.remote.dataCenter.DataCenterService
import javax.inject.Inject

class PerkRepositoryImpl @Inject constructor(
    private val dataCenterService: DataCenterService,
    private val perkDao: PerkDao,
) : PerkRepository {
    override suspend fun getPerk(id: Int): PerkEntity {
        return perkDao.get(id)
    }

    override suspend fun getPerksList(): List<PerkDataModel> {
        val list = mutableListOf<PerkDataModel>()
        dataCenterService
            .getPerks()
            .forEach {
                list.add(PerkDataModel(it))
                it.slots.forEach { slot ->
                    list.addAll(PerkDataModel(slot.runes))
                }
            }
        return list.toList()
    }

    override suspend fun addPerksList(entity: List<PerkEntity>) {
        perkDao.insert(entity)
    }

    override suspend fun addPerk(entity: PerkEntity) {
        perkDao.insert(entity)
    }
}
