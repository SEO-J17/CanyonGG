package io.github.seoj17.domain.usecase.bookmark

import dagger.Reusable
import io.github.seoj17.data.repository.champion.ChampionBookmarkRepository
import io.github.seoj17.domain.model.ChampionBookmarkDomainModel
import javax.inject.Inject

@Reusable
class AddBookmarkChampionUseCase @Inject constructor(
    private val championBookmarkRepository: ChampionBookmarkRepository,
) {

    suspend operator fun invoke(champion: ChampionBookmarkDomainModel) {
        championBookmarkRepository.addBookmarkChampion(ChampionBookmarkDomainModel.toEntity(champion))
    }
}
