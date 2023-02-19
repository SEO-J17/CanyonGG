package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.ChampionsDao
import io.github.seoj17.canyongg.data.local.ChampionsEntity
import io.github.seoj17.canyongg.data.model.DataChampions
import io.github.seoj17.canyongg.data.remote.DataCenterService
import javax.inject.Inject

class ChampionsRepositoryImpl @Inject constructor(
    private val dataCenterService: DataCenterService,
    private val championsDao: ChampionsDao,
) : ChampionsRepository {
    override suspend fun getChampionList(): List<DataChampions> {
        return dataCenterService
            .getChamps()
            .data
            .toList()
            .map {
                DataChampions(
                    key = it.second.key.toInt(),
                    name = it.first
                )
            }
    }

    override suspend fun getChampion(id: Int): String {
        return championsDao.getChampion(id).name
    }

    override suspend fun addChampionList(entities: List<ChampionsEntity>) {
        championsDao.insert(entities)
    }

    override suspend fun addChampion(entity: ChampionsEntity) {
        championsDao.insert(entity)
    }
}