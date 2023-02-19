package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.local.PerksEntity
import io.github.seoj17.canyongg.data.repository.PerksRepository
import io.github.seoj17.canyongg.domain.model.DomainPerks
import javax.inject.Inject

@Reusable
class AddPerksListUseCase @Inject constructor(
    private val repository: PerksRepository
) {
    suspend operator fun invoke(domain: List<DomainPerks>) {
        repository.addPerksList(
            domain.map {
                PerksEntity(
                    id = it.id,
                    name = it.name,
                    imgUrl = it.imgUrl,
                )
            }
        )
    }
}