package io.github.seoj17.canyongg.domain

import io.github.seoj17.canyongg.data.repository.SummonerRepository
import javax.inject.Inject

class DeleteAllRecentSummonerUseCase @Inject constructor(
    private val repository: SummonerRepository
) {
    suspend operator fun invoke() {
        return repository.deleteAllRecentSummoners()
    }
}