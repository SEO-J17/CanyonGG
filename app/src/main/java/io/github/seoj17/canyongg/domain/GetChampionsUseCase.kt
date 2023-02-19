package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.ChampionsRepository
import io.github.seoj17.canyongg.domain.model.DomainChampions
import javax.inject.Inject

@Reusable
class GetChampionsUseCase @Inject constructor(
    private val repository: ChampionsRepository
) {
    suspend operator fun invoke(): List<DomainChampions> {
        return repository
            .getChampionList()
            .map {
                DomainChampions(it.key, it.name)
            }
    }
}