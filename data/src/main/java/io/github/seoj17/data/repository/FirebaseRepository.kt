package io.github.seoj17.data.repository

import com.google.firebase.remoteconfig.FirebaseRemoteConfig

interface FirebaseRepository {
    fun getRemoteConfig(): FirebaseRemoteConfig
}
