package io.github.seoj17.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.data.repository.champion.ChampionsRepository
import io.github.seoj17.domain.model.ChampionsDomainModel
import javax.inject.Inject

@Reusable
class AddChampionListUseCase @Inject constructor(
    private val repository: ChampionsRepository,
) {
    suspend operator fun invoke(championList: List<ChampionsDomainModel>) {
        repository.addChampionList(
            ChampionsDomainModel.toEntity(championList),
        )
    }
}
