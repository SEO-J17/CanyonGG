package io.github.seoj17.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.data.repository.champion.ChampionsRepository
import io.github.seoj17.domain.model.ChampionsDomainModel
import javax.inject.Inject

@Reusable
class GetChampionsUseCase @Inject constructor(
    private val repository: ChampionsRepository,
) {
    suspend operator fun invoke(): List<ChampionsDomainModel> {
        return repository
            .getChampionList()
            .map {
                ChampionsDomainModel(it)
            }
    }
}
