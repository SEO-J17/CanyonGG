package io.github.seoj17.canyongg.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.github.seoj17.domain.usecase.perks.AddPerksListUseCase
import io.github.seoj17.domain.usecase.perks.GetPerksListUseCase

@HiltWorker
class PerkListFetchWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val getPerksListUseCase: GetPerksListUseCase,
    private val addPerksListUseCase: AddPerksListUseCase,
) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        runCatching {
            getPerksListUseCase()
        }.fold(
            onSuccess = {
                addPerksListUseCase(it)
                return Result.success()
            },
            onFailure = {
                return Result.retry()
            },
        )
    }
}
