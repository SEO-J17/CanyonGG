package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.PerksRepository
import io.github.seoj17.canyongg.domain.model.DomainPerks
import javax.inject.Inject

@Reusable
class GetPerkUseCase @Inject constructor(
    private val repository: PerksRepository
) {
    suspend operator fun invoke(id: Int): DomainPerks {
        return DomainPerks(repository.getPerk(id))
    }
}