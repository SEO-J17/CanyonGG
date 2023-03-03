package io.github.seoj17.canyongg

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import dagger.hilt.android.HiltAndroidApp
import io.github.seoj17.canyongg.worker.ChampionFetchWorker
import io.github.seoj17.canyongg.worker.PerkListFetchWorker
import javax.inject.Inject

@HiltAndroidApp
class MyApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory
    override fun onCreate() {
        super.onCreate()
        val championRequest = OneTimeWorkRequestBuilder<ChampionFetchWorker>().build()
        val perkListRequest = OneTimeWorkRequestBuilder<PerkListFetchWorker>().build()

        WorkManager
            .getInstance(this)
            .beginWith(listOf(championRequest, perkListRequest))
            .enqueue()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration
            .Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}