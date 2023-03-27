package io.github.seoj17.domain.repositoryImpl

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import io.github.seoj17.data.R
import io.github.seoj17.data.contract.RemoteConfigContract
import io.github.seoj17.data.repository.FirebaseRepository
import javax.inject.Inject

class FirebaseRepositoryImpl @Inject constructor(
    private val remoteConfig: FirebaseRemoteConfig,
) : FirebaseRepository {
    override fun getRemoteConfig(): FirebaseRemoteConfig {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = RemoteConfigContract.MINIMUM_FETCH_INTERVAL_TIME
        }
        return remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(R.xml.remote_config_defaults)
        }
    }
}
