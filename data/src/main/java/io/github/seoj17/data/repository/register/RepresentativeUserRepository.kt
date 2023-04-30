package io.github.seoj17.data.repository.register

import io.github.seoj17.data.local.user.RegisterUserInfoEntity
import io.github.seoj17.data.model.RegisterUserDataModel
import kotlinx.coroutines.flow.Flow

interface RepresentativeUserRepository {
    fun getMyUserInfo(): Flow<RegisterUserDataModel?>
    suspend fun addMyUserInfo(entity: RegisterUserInfoEntity)
    suspend fun deleteMyUserChamps()
}
