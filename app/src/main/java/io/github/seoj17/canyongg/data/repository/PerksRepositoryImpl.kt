package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.perks.PerksDao
import io.github.seoj17.canyongg.data.local.perks.PerksEntity
import io.github.seoj17.canyongg.data.model.DataPerks
import io.github.seoj17.canyongg.data.remote.DataCenterService
import javax.inject.Inject

class PerksRepositoryImpl @Inject constructor(
    private val dataCenterService: DataCenterService,
    private val perksDao: PerksDao,
) : PerksRepository {
    override suspend fun getPerk(id: Int): PerksEntity {
        return perksDao.getPerk(id)
    }

    override suspend fun getPerksList(): List<DataPerks> {
        val list = mutableListOf<DataPerks>()
        dataCenterService.getPerks().forEach {
            list.add(DataPerks(it))
            it.slots.forEach { slot ->
                list.addAll(DataPerks(slot.runes))
            }
        }
        return list.toList()
    }

    override suspend fun addPerksList(entity: List<PerksEntity>) {
        perksDao.insert(entity)
    }

    override suspend fun addPerk(entity: PerksEntity) {
        perksDao.insert(entity)
    }
}