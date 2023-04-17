package io.github.seoj17.domain.usecase.perks

import dagger.Reusable
import io.github.seoj17.data.repository.perk.PerkRepository
import io.github.seoj17.domain.model.PerkDomainModel
import javax.inject.Inject

@Reusable
class GetPerksListUseCase @Inject constructor(
    private val repository: PerkRepository,
) {
    suspend operator fun invoke(): List<PerkDomainModel> {
        return repository
            .getPerksList()
            .map {
                PerkDomainModel(it)
            }
    }
}
