package io.github.seoj17.data.repository.datacenter

import io.github.seoj17.data.remote.dataCenter.DataCenterService
import javax.inject.Inject

class DataCenterRepositoryImpl @Inject constructor(
    private val dataCenterService: DataCenterService,
) : DataCenterRepository {
    override suspend fun getSpell(key: Int): String? {
        return dataCenterService
            .getSpells()
            ?.let { response ->
                response
                    .data
                    .filter { it.value.key.toInt() == key }
                    .keys
                    .first()
            }
    }
}
