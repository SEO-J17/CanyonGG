package io.github.seoj17.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.data.repository.champion.ChampionsRepository
import io.github.seoj17.domain.model.ChampionsDomainModel
import javax.inject.Inject

@Reusable
class GetAllChampionUseCase @Inject constructor(
    private val championsRepository: ChampionsRepository,
) {
    suspend operator fun invoke(): List<ChampionsDomainModel> {
        return championsRepository
            .getAllChampion()
            .map {
                ChampionsDomainModel(it)
            }
    }
}
