package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.MyMostChampEntity
import io.github.seoj17.canyongg.data.local.MyUserInfoEntity
import kotlinx.coroutines.flow.Flow

interface MyUserRepository {
    fun getMyUserInfo(): Flow<MyUserInfoEntity?>
    fun getMyMostChamps(): Flow<MyMostChampEntity?>
    suspend fun addMyUserInfo(entity: MyUserInfoEntity)
    suspend fun addMostChamps(entity: MyMostChampEntity)
    suspend fun deleteMostChamps()
    suspend fun deleteMyUserChamps()
}