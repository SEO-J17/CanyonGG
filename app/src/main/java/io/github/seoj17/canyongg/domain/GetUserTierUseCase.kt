package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.model.SummonerTier
import io.github.seoj17.canyongg.data.repository.InfoRepository
import javax.inject.Inject

@Reusable
class GetUserTierUseCase @Inject constructor(
    private val repository: InfoRepository
) {
    suspend operator fun invoke(id: String): SummonerTier? {
        return repository.getTier(id)
    }
}