package io.github.seoj17.canyongg.domain.usecase.perks

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.PerkRepository
import io.github.seoj17.canyongg.domain.model.PerkDomainModel
import javax.inject.Inject

@Reusable
class AddPerksListUseCase @Inject constructor(
    private val repository: PerkRepository,
) {
    suspend operator fun invoke(domain: List<PerkDomainModel>) {
        repository.addPerksList(
            PerkDomainModel.toEntity(domain)
        )
    }
}