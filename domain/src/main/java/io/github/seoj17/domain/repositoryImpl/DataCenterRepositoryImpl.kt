package io.github.seoj17.domain.repositoryImpl

import io.github.seoj17.data.remote.dataCenter.DataCenterService
import io.github.seoj17.data.repository.DataCenterRepository
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
