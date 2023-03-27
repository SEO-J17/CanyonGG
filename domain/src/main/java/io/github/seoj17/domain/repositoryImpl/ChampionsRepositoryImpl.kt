package io.github.seoj17.domain.repositoryImpl

import io.github.seoj17.data.local.champions.ChampionsDao
import io.github.seoj17.data.local.champions.ChampionsEntity
import io.github.seoj17.data.model.ChampionsDataModel
import io.github.seoj17.data.remote.dataCenter.DataCenterService
import io.github.seoj17.data.repository.ChampionsRepository
import javax.inject.Inject

class ChampionsRepositoryImpl @Inject constructor(
    private val dataCenterService: DataCenterService,
    private val championsLocal: ChampionsDao,
) : ChampionsRepository {
    override suspend fun getChampionList(): List<ChampionsDataModel> {
        return dataCenterService
            .getChamps()
            .data
            .toList()
            .map {
                ChampionsDataModel(
                    key = it.second.key.toInt(),
                    name = it.first,
                )
            }
    }

    override suspend fun getChampion(id: Int): String {
        return championsLocal.get(id).name
    }

    override suspend fun addChampionList(entities: List<ChampionsEntity>) {
        championsLocal.insert(entities)
    }

    override suspend fun addChampion(entity: ChampionsEntity) {
        championsLocal.insert(entity)
    }
}
