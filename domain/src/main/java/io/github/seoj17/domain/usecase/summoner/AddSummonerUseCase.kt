package io.github.seoj17.domain.usecase.summoner

import dagger.Reusable
import io.github.seoj17.data.repository.SummonerRepository
import javax.inject.Inject

@Reusable
class AddSummonerUseCase @Inject constructor(
    private val repository: SummonerRepository,
) {
    suspend operator fun invoke(puuid: String, name: String) {
        return repository.addRecentSummoner(puuid, name)
    }
}
