package io.github.seoj17.domain.usecase.match

import dagger.Reusable
import io.github.seoj17.data.repository.match.MatchRepository
import io.github.seoj17.domain.model.MatchDomainModel
import javax.inject.Inject

@Reusable
class GetMatchUseCase @Inject constructor(
    private val repository: MatchRepository,
) {
    suspend operator fun invoke(matchId: String): Result<MatchDomainModel> {
        return runCatching {
            MatchDomainModel(
                repository.getMatchInfo(matchId),
            )
        }
    }
}
