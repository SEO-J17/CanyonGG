package io.github.seoj17.canyongg.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : DataStoreRepository {

    override suspend fun addThemeSetting(key: String, settingValue: Int) {
        dataStore.edit {
            it[intPreferencesKey(key)] = settingValue
        }
    }

    override fun getThemeSetting(key: String): Flow<Int?> {
        return dataStore
            .data
            .map {
                it[intPreferencesKey(key)]
            }
    }
}
