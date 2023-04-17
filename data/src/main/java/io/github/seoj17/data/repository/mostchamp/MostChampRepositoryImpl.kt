package io.github.seoj17.data.repository.mostchamp

import io.github.seoj17.data.local.user.RegisterUserMostChampDao
import io.github.seoj17.data.local.user.RegisterUserMostChampEntity
import io.github.seoj17.data.model.MostChampionDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MostChampRepositoryImpl @Inject constructor(
    private val mostChampDao: RegisterUserMostChampDao,
) : MostChampRepository {
    override fun getMyMostChamps(): Flow<List<MostChampionDataModel>> {
        return mostChampDao
            .get()
            .map {
                MostChampionDataModel(it)
            }
    }

    override suspend fun addMostChamps(entity: List<RegisterUserMostChampEntity>) {
        mostChampDao.insert(entity)
    }

    override suspend fun deleteMostChamps() {
        mostChampDao.deleteAll()
    }
}
