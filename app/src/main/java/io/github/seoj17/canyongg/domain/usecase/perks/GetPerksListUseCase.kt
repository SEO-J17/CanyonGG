package io.github.seoj17.canyongg.domain.usecase.perks

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.PerksRepository
import io.github.seoj17.canyongg.domain.model.DomainPerks
import javax.inject.Inject

@Reusable
class GetPerksListUseCase @Inject constructor(
    private val repository: PerksRepository,
) {
    suspend operator fun invoke(): List<DomainPerks> {
        return repository.getPerksList().map {
            DomainPerks(it)
        }
    }
}