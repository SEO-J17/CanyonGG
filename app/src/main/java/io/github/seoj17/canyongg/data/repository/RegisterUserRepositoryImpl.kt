package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.user.RegisterUserMostChampDao
import io.github.seoj17.canyongg.data.local.user.RegisterUserMostChampEntity
import io.github.seoj17.canyongg.data.local.user.RegisterUserInfoDao
import io.github.seoj17.canyongg.data.local.user.RegisterUserInfoEntity
import io.github.seoj17.canyongg.data.model.MostChampionDataModel
import io.github.seoj17.canyongg.data.model.RegisterUserDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RegisterUserRepositoryImpl @Inject constructor(
    private val registerUserLocalService: RegisterUserInfoDao,
    private val mostChampLocalService: RegisterUserMostChampDao,
) : RegisterUserRepository {
    override fun getMyUserInfo(): Flow<RegisterUserDataModel?> {
        return registerUserLocalService
            .get()
            .map {
                it?.let { entity -> RegisterUserDataModel(entity) }
            }
    }

    override fun getMyMostChamps(): Flow<List<MostChampionDataModel>> {
        return mostChampLocalService
            .get()
            .map {
                MostChampionDataModel(it)
            }
    }

    override suspend fun addMyUserInfo(entity: RegisterUserInfoEntity) {
        registerUserLocalService.insert(entity)
    }

    override suspend fun addMostChamps(entity: List<RegisterUserMostChampEntity>) {
        mostChampLocalService.insert(entity)
    }

    override suspend fun deleteMostChamps() {
        mostChampLocalService.deleteAll()
    }

    override suspend fun deleteMyUserChamps() {
        registerUserLocalService.deleteAll()
    }
}