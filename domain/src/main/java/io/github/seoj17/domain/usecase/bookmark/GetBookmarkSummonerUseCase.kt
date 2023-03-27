package io.github.seoj17.domain.usecase.bookmark

import dagger.Reusable
import io.github.seoj17.data.repository.SummonerBookmarkRepository
import io.github.seoj17.domain.model.BookmarkSummonerDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetBookmarkSummonerUseCase @Inject constructor(
    private val repository: SummonerBookmarkRepository,
) {
    operator fun invoke(): Flow<List<BookmarkSummonerDomainModel>> {
        return repository.getBookmarkedSummoner().map {
            BookmarkSummonerDomainModel(it)
        }
    }
}
