package io.github.seoj17.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.data.repository.SummonerRepository
import javax.inject.Inject

@Reusable
class GetRotationChampUseCase @Inject constructor(
    private val repository: SummonerRepository,
) {
    suspend operator fun invoke(): List<Int> {
        return repository.getRotationChamps()
    }
}