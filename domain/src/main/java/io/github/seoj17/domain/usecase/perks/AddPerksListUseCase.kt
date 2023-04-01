package io.github.seoj17.domain.usecase.perks

import dagger.Reusable
import io.github.seoj17.data.repository.PerkRepository
import io.github.seoj17.domain.model.PerkDomainModel
import javax.inject.Inject

@Reusable
class AddPerksListUseCase @Inject constructor(
    private val repository: PerkRepository,
) {
    suspend operator fun invoke(domain: List<PerkDomainModel>) {
        repository.addPerksList(
            PerkDomainModel.toEntity(domain),
        )
    }
}
