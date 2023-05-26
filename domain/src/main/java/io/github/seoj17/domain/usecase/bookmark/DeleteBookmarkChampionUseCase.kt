package io.github.seoj17.domain.usecase.bookmark

import dagger.Reusable
import io.github.seoj17.data.repository.champion.ChampionBookmarkRepository
import javax.inject.Inject

@Reusable
class DeleteBookmarkChampionUseCase @Inject constructor(
    private val bookmarkRepository: ChampionBookmarkRepository,
) {
    suspend operator fun invoke(key: Int) {
        bookmarkRepository.deleteBookmarkChampion(key)
    }
}
