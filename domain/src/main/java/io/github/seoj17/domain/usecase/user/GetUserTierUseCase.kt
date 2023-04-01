package io.github.seoj17.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.data.repository.SummonerRepository
import io.github.seoj17.domain.model.SummonerTierDomainModel
import javax.inject.Inject

@Reusable
class GetUserTierUseCase @Inject constructor(
    private val repository: SummonerRepository,
) {
    suspend operator fun invoke(id: String): SummonerTierDomainModel? {
        return repository
            .getTier(id)
            ?.let {
                SummonerTierDomainModel(it)
            }
    }
}
