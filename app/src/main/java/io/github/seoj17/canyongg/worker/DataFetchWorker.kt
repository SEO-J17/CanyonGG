package io.github.seoj17.canyongg.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.github.seoj17.canyongg.domain.AddChampionListUseCase
import io.github.seoj17.canyongg.domain.AddPerksListUseCase
import io.github.seoj17.canyongg.domain.GetChampionsUseCase
import io.github.seoj17.canyongg.domain.GetPerksListUseCase

@HiltWorker
class DataFetchWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getChampionsUseCase: GetChampionsUseCase,
    private val addChampionListUseCase: AddChampionListUseCase,
    private val getPerksListUseCase: GetPerksListUseCase,
    private val addPerksListUseCase: AddPerksListUseCase,
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        runCatching {
            addChampionListUseCase(getChampionsUseCase())
            addPerksListUseCase(getPerksListUseCase())
        }.fold(
            onSuccess = {
                return Result.success()
            },
            onFailure = {
                return Result.retry()
            }
        )
    }
}