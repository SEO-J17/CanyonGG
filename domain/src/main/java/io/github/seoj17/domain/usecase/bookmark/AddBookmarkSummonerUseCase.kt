package io.github.seoj17.domain.usecase.bookmark

import dagger.Reusable
import io.github.seoj17.data.repository.summoner.SummonerBookmarkRepository
import io.github.seoj17.domain.model.BookmarkSummonerDomainModel
import javax.inject.Inject

@Reusable
class AddBookmarkSummonerUseCase @Inject constructor(
    private val repository: SummonerBookmarkRepository,
) {
    suspend operator fun invoke(domain: BookmarkSummonerDomainModel) {
        repository.addBookmarkSummoner(
            BookmarkSummonerDomainModel.toEntity(domain),
        )
    }
}
