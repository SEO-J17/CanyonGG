package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.user.RegisterUserMostChampEntity
import io.github.seoj17.canyongg.data.local.user.RegisterUserInfoEntity
import io.github.seoj17.canyongg.data.model.MostChampionDataModel
import io.github.seoj17.canyongg.data.model.RegisterUserDataModel
import kotlinx.coroutines.flow.Flow

interface RegisterUserRepository {
    fun getMyUserInfo(): Flow<RegisterUserDataModel?>
    fun getMyMostChamps(): Flow<List<MostChampionDataModel>>
    suspend fun addMyUserInfo(entity: RegisterUserInfoEntity)
    suspend fun addMostChamps(entity: List<RegisterUserMostChampEntity>)
    suspend fun deleteMostChamps()
    suspend fun deleteMyUserChamps()
}