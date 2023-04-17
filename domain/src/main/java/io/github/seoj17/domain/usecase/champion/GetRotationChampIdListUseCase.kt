package io.github.seoj17.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.data.repository.summoner.SummonerRepository
import javax.inject.Inject

@Reusable
class GetRotationChampIdListUseCase @Inject constructor(
    private val repository: SummonerRepository,
) {
    suspend operator fun invoke(): List<Int> {
        return repository.getRotationChamps()
    }
}
