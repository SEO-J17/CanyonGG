package io.github.seoj17.domain.usecase.recent.search

import dagger.Reusable
import io.github.seoj17.data.repository.summoner.SummonerRepository
import javax.inject.Inject

@Reusable
class DeleteAllRecentSummonerUseCase @Inject constructor(
    private val repository: SummonerRepository,
) {
    suspend operator fun invoke() {
        return repository.deleteAllRecentSummoners()
    }
}
