package io.github.seoj17.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.data.repository.champion.ChampionsRepository
import io.github.seoj17.domain.model.ChampionsDomainModel
import javax.inject.Inject

@Reusable
class GetChampionDetailUseCase @Inject constructor(
    private val championsRepository: ChampionsRepository,
) {
    suspend operator fun invoke(key: Int): ChampionsDomainModel? {
        return championsRepository
            .getChampion(key)
            ?.let {
                ChampionsDomainModel(it)
            }
    }
}
