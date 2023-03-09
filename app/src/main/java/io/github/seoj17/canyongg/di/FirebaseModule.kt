package io.github.seoj17.canyongg.di

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.contract.RemoteConfigContract
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {
    @Singleton
    @Provides
    fun provideFirebaseRemoteConfig(): FirebaseRemoteConfig {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = RemoteConfigContract.MINIMUM_FETCH_INTERVAL_TIME
        }
        return Firebase
            .remoteConfig
            .apply {
                setConfigSettingsAsync(configSettings)
                setDefaultsAsync(R.xml.remote_config_defaults)
            }
    }

    @Provides
    fun provideApiVersion(
        remoteConfig: FirebaseRemoteConfig,
    ): String {
        return remoteConfig.getString(RemoteConfigContract.REMOTE_CONFIG_KEY)
    }
}