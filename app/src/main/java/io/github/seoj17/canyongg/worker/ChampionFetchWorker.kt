package io.github.seoj17.canyongg.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.github.seoj17.canyongg.domain.usecase.champion.AddChampionListUseCase
import io.github.seoj17.canyongg.domain.usecase.champion.GetChampionsUseCase

@HiltWorker
class ChampionFetchWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getChampionsUseCase: GetChampionsUseCase,
    private val addChampionListUseCase: AddChampionListUseCase,
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        runCatching {
            getChampionsUseCase()
        }.fold(
            onSuccess = {
                addChampionListUseCase(it)
                return Result.success()
            },
            onFailure = {
                return Result.retry()
            },
        )
    }
}
