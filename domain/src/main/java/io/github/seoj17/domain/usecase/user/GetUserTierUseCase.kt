package io.github.seoj17.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.data.repository.summoner.SummonerRepository
import io.github.seoj17.domain.model.SummonerTierDomainModel
import javax.inject.Inject

@Reusable
class GetUserTierUseCase @Inject constructor(
    private val repository: SummonerRepository,
) {
    suspend operator fun invoke(id: String): Result<SummonerTierDomainModel?> {
        return runCatching {
            repository
                .getTier(id)
                ?.let {
                    SummonerTierDomainModel(it)
                }
        }
    }
}
