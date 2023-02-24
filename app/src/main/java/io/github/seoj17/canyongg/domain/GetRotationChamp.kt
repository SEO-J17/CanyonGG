package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.SummonerRepository
import javax.inject.Inject

@Reusable
class GetRotationChamp @Inject constructor(
    private val repository: SummonerRepository
) {
    suspend operator fun invoke(): List<Int> {
        return repository.getRotationChamps()
    }
}