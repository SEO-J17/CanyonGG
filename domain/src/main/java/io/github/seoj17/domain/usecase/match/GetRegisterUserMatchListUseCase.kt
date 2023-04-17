package io.github.seoj17.domain.usecase.match

import dagger.Reusable
import io.github.seoj17.data.repository.match.MatchRepository
import io.github.seoj17.domain.model.RepresentativeInfoDomainModel
import javax.inject.Inject

@Reusable
class GetRegisterUserMatchListUseCase @Inject constructor(
    private val repository: MatchRepository,
    private val getMatchUseCase: GetMatchUseCase,
) {
    suspend operator fun invoke(
        puuid: String,
        start: Int = 0,
    ): List<RepresentativeInfoDomainModel?> {
        return repository.getMatchId(puuid, start).map { matchId ->
            getMatchUseCase(matchId)
                .info
                .participants
                .find { it.puuid == puuid }
                ?.let {
                    RepresentativeInfoDomainModel(it)
                }
        }
    }
}
