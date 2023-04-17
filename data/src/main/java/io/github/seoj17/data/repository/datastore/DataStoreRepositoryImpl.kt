package io.github.seoj17.data.repository.datastore

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

    override suspend fun addThemeSetting(settingValue: Int) {
        dataStore.edit {
            it[intPreferencesKey(THEME_KEY)] = settingValue
        }
    }

    override fun getThemeSetting(): Flow<Int?> {
        return dataStore
            .data
            .map {
                it[intPreferencesKey(THEME_KEY)]
            }
    }

    companion object {
        private const val THEME_KEY = "theme_mode"
    }
}
