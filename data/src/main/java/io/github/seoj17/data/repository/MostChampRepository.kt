package io.github.seoj17.data.repository

import io.github.seoj17.data.local.user.RegisterUserMostChampEntity
import io.github.seoj17.data.model.MostChampionDataModel
import kotlinx.coroutines.flow.Flow

interface MostChampRepository {
    fun getMyMostChamps(): Flow<List<MostChampionDataModel>>
    suspend fun addMostChamps(entity: List<RegisterUserMostChampEntity>)
    suspend fun deleteMostChamps()
}
