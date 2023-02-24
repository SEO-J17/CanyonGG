package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.ChampionsRepository
import javax.inject.Inject

@Reusable
class GetChampionName @Inject constructor(
    private val repository: ChampionsRepository
) {
    suspend operator fun invoke(ids: List<Int>): List<String> {
        return ids.map { id ->
            repository.getChampion(id)
        }

    }
}