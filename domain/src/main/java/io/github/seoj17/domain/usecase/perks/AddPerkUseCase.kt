package io.github.seoj17.domain.usecase.perks

import io.github.seoj17.data.repository.PerkRepository
import io.github.seoj17.domain.model.PerkDomainModel
import javax.inject.Inject

class AddPerkUseCase @Inject constructor(
    private val repository: PerkRepository,
) {
    suspend operator fun invoke(domain: PerkDomainModel) {
        repository.addPerk(
            PerkDomainModel.toEntity(domain),
        )
    }
}
