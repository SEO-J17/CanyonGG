package io.github.seoj17.canyongg.domain

import io.github.seoj17.canyongg.data.repository.SummonerRepository
import javax.inject.Inject

class AddSummonerUseCase @Inject constructor(
    private val repository: SummonerRepository
) {
    suspend operator fun invoke(puuid: String, name: String) {
        return repository.addRecentSummoner(puuid, name)
    }
}