package io.github.seoj17.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.data.repository.champion.ChampionsRepository
import javax.inject.Inject

@Reusable
class GetChampionNameUseCase @Inject constructor(
    private val repository: ChampionsRepository,
) {
    suspend operator fun invoke(ids: List<Int>): List<String> {
        return ids.map { id ->
            repository
                .getChampion(id)
                ?.id
                ?: ""
        }
            .filter { it != "" }
    }
}
