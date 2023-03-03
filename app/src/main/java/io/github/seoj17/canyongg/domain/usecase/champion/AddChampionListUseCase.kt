package io.github.seoj17.canyongg.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.ChampionsRepository
import io.github.seoj17.canyongg.domain.model.ChampionsDomainModel
import javax.inject.Inject

@Reusable
class AddChampionListUseCase @Inject constructor(
    private val repository: ChampionsRepository,
) {
    suspend operator fun invoke(championList: List<ChampionsDomainModel>) {
        repository.addChampionList(
            ChampionsDomainModel.toEntity(championList)
        )
    }
}