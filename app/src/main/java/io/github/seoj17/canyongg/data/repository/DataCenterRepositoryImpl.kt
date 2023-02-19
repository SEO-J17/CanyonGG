package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.remote.DataCenterService
import javax.inject.Inject

class DataCenterRepositoryImpl @Inject constructor(
    private val dataCenterService: DataCenterService,
) : DataCenterRepository {
    override suspend fun getSpell(key: Int): String {
        return dataCenterService
            .getSpells()
            .data
            .filter { it.value.key.toInt() == key }
            .keys
            .first()
    }

}