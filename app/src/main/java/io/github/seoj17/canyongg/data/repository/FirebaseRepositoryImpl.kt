package io.github.seoj17.canyongg.data.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.contract.RemoteConfigContract
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
