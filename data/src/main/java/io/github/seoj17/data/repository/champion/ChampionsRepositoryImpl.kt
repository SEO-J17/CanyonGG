package io.github.seoj17.data.repository.champion

import io.github.seoj17.data.local.champions.ChampionsDao
import io.github.seoj17.data.local.champions.ChampionsEntity
import io.github.seoj17.data.model.ChampionsDataModel
import io.github.seoj17.data.remote.dataCenter.DataCenterService
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
                ChampionsDataModel(it.second)
            }
    }

    override suspend fun getChampion(id: Int): ChampionsDataModel? {
        return championsLocal
            .get(id)
            ?.let {
                ChampionsDataModel(it)
            }
    }

    override suspend fun getAllChampion(): List<ChampionsDataModel> {
        return championsLocal
            .getAll()
            .map {
                ChampionsDataModel(it)
            }
    }

    override suspend fun addChampionList(entities: List<ChampionsEntity>) {
        championsLocal.insert(entities)
    }

    override suspend fun addChampion(entity: ChampionsEntity) {
        championsLocal.insert(entity)
    }
}
