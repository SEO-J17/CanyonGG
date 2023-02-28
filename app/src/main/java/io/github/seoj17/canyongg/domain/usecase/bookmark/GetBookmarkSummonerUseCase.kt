package io.github.seoj17.canyongg.domain.usecase.bookmark

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.SummonerBookmarkRepository
import io.github.seoj17.canyongg.domain.model.DomainBookmarkSummoner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetBookmarkSummonerUseCase @Inject constructor(
    private val repository: SummonerBookmarkRepository,
) {
    operator fun invoke(): Flow<List<DomainBookmarkSummoner>> {
        return repository.getBookmarkedSummoner().map {
            DomainBookmarkSummoner(it)
        }
    }
}