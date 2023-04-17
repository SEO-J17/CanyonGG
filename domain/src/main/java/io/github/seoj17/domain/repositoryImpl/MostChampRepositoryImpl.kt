package io.github.seoj17.domain.repositoryImpl

import io.github.seoj17.data.local.user.RegisterUserMostChampDao
import io.github.seoj17.data.local.user.RegisterUserMostChampEntity
import io.github.seoj17.data.model.MostChampionDataModel
import io.github.seoj17.data.repository.MostChampRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MostChampRepositoryImpl @Inject constructor(
    private val mostChampLocalService: RegisterUserMostChampDao,
) : MostChampRepository {
    override fun getMyMostChamps(): Flow<List<MostChampionDataModel>> {
        return mostChampLocalService
            .get()
            .map {
                MostChampionDataModel(it)
            }
    }

    override suspend fun addMostChamps(entity: List<RegisterUserMostChampEntity>) {
        mostChampLocalService.insert(entity)
    }

    override suspend fun deleteMostChamps() {
        mostChampLocalService.deleteAll()
    }
}
