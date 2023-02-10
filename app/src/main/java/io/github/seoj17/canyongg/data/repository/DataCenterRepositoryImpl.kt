package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.remote.DataCenterService
import javax.inject.Inject

class DataCenterRepositoryImpl @Inject constructor(
    private val dataCenterService: DataCenterService
) : DataCenterRepository {
    override suspend fun getSpell(key: Int): String {
        return dataCenterService
            .getSpells()
            .data
            .filter { it.value.key.toInt() == key }
            .keys
            .first()
    }

    override suspend fun getMainPerk(mainId: Int, detailId: Int): String {
        return dataCenterService
            .getPerks()
            .find { it.id == mainId }
            ?.slots?.get(0)
            ?.runes?.get(0)
            ?.icon
            ?: ""
    }

    override suspend fun getSubPerk(id: Int): String {
        return dataCenterService.getPerks()
            .find { it.id == id }
            ?.icon
            ?: ""
    }

    override suspend fun getChamp(champId: Int): String {
        return dataCenterService
            .getChamps()
            .data
            .filter { it.value.key.toInt() == champId }
            .keys
            .first()
    }
}