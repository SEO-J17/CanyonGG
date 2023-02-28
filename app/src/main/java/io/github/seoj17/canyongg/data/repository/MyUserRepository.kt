package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.user.MyMostChampEntity
import io.github.seoj17.canyongg.data.local.user.MyUserInfoEntity
import kotlinx.coroutines.flow.Flow

interface MyUserRepository {
    fun getMyUserInfo(): Flow<MyUserInfoEntity?>
    fun getMyMostChamps(): Flow<List<MyMostChampEntity>>
    suspend fun addMyUserInfo(entity: MyUserInfoEntity)
    suspend fun addMostChamps(entity: List<MyMostChampEntity>)
    suspend fun deleteMostChamps()
    suspend fun deleteMyUserChamps()
}