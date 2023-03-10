package io.github.seoj17.canyongg.domain.usecase.bookmark

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.SummonerBookmarkRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class CheckBookmarkedSummonerUseCase @Inject constructor(
    private val repository: SummonerBookmarkRepository,
) {
    operator fun invoke(puuid: String): Flow<Boolean> {
        return repository.checkBookmarkedSummoner(puuid)
    }
}