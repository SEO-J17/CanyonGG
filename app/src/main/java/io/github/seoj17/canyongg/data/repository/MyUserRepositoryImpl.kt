package io.github.seoj17.canyongg.data.repository

import io.github.seoj17.canyongg.data.local.user.MyMostChampDao
import io.github.seoj17.canyongg.data.local.user.MyMostChampEntity
import io.github.seoj17.canyongg.data.local.user.MyUserInfoDao
import io.github.seoj17.canyongg.data.local.user.MyUserInfoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MyUserRepositoryImpl @Inject constructor(
    private val myUserLocalService: MyUserInfoDao,
    private val mostChampLocalService: MyMostChampDao,
) : MyUserRepository {
    override fun getMyUserInfo(): Flow<MyUserInfoEntity?> {
        return myUserLocalService.getMyUserInfo()
    }

    override fun getMyMostChamps(): Flow<List<MyMostChampEntity>> {
        return mostChampLocalService.getMostChamps()
    }

    override suspend fun addMyUserInfo(entity: MyUserInfoEntity) {
        myUserLocalService.insert(entity)
    }

    override suspend fun addMostChamps(entity: List<MyMostChampEntity>) {
        mostChampLocalService.insert(entity)
    }

    override suspend fun deleteMostChamps() {
        mostChampLocalService.deleteAll()
    }

    override suspend fun deleteMyUserChamps() {
        myUserLocalService.deleteAll()
    }
}