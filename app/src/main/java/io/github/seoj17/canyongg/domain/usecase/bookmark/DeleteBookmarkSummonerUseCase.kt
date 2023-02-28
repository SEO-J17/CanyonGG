package io.github.seoj17.canyongg.domain.usecase.bookmark

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.SummonerBookmarkRepository
import javax.inject.Inject

@Reusable
class DeleteBookmarkSummonerUseCase @Inject constructor(
    private val repository: SummonerBookmarkRepository,
) {
    suspend operator fun invoke(name: String) {
        repository.deleteBookmarkSummoner(name)
    }
}
