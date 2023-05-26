package io.github.seoj17.domain.usecase.bookmark

import dagger.Reusable
import io.github.seoj17.data.repository.champion.ChampionBookmarkRepository
import io.github.seoj17.domain.model.ChampionBookmarkDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetBookmarkChampionUseCase @Inject constructor(
    private val championBookmarkRepository: ChampionBookmarkRepository,
) {
    operator fun invoke(): Flow<List<ChampionBookmarkDomainModel>> {
        return championBookmarkRepository
            .getChampion()
            .map {
                ChampionBookmarkDomainModel(it)
            }
    }
}
