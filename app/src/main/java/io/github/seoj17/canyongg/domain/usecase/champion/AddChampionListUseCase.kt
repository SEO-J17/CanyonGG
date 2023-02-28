package io.github.seoj17.canyongg.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.canyongg.data.local.champions.ChampionsEntity
import io.github.seoj17.canyongg.data.repository.ChampionsRepository
import io.github.seoj17.canyongg.domain.model.DomainChampions
import javax.inject.Inject

@Reusable
class AddChampionListUseCase @Inject constructor(
    private val repository: ChampionsRepository,
) {
    suspend operator fun invoke(championList: List<DomainChampions>) {
        repository.addChampionList(
            championList.map {
                ChampionsEntity(it.key, it.name)
            }
        )
    }
}