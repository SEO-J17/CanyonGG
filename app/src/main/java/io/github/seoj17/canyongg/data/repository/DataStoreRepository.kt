package io.github.seoj17.canyongg.data.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun addThemeSetting(key: String, settingValue: Int)
    fun getThemeSetting(key: String): Flow<Int?>
}
