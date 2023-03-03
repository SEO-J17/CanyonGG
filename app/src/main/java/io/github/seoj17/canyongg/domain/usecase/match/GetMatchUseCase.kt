package io.github.seoj17.canyongg.domain.usecase.match

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.MatchRepository
import io.github.seoj17.canyongg.domain.model.MatchDomainModel
import javax.inject.Inject

@Reusable
class GetMatchUseCase @Inject constructor(
    private val repository: MatchRepository,
) {
    suspend operator fun invoke(matchId: String): MatchDomainModel {
        return MatchDomainModel(repository.getMatchInfo(matchId))
    }
}