package io.github.seoj17.data.repository.datastore

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun addThemeSetting(settingValue: Int)
    fun getThemeSetting(): Flow<Int?>
}
