package io.github.seoj17.data.repository.register

import io.github.seoj17.data.local.user.RegisterUserInfoDao
import io.github.seoj17.data.local.user.RegisterUserInfoEntity
import io.github.seoj17.data.model.RegisterUserDataModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RegisterUserRepositoryImpl @Inject constructor(
    private val registerUserLocalService: RegisterUserInfoDao,
) : RegisterUserRepository {
    override fun getMyUserInfo(): Flow<RegisterUserDataModel?> {
        return registerUserLocalService
            .get()
            .map {
                it?.let { entity -> RegisterUserDataModel(entity) }
            }
    }

    override suspend fun addMyUserInfo(entity: RegisterUserInfoEntity) {
        registerUserLocalService.insert(entity)
    }

    override suspend fun deleteMyUserChamps() {
        registerUserLocalService.deleteAll()
    }
}
