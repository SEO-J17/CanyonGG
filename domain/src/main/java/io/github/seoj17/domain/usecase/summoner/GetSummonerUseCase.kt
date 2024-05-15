package io.github.seoj17.domain.usecase.summoner

import dagger.Reusable
import io.github.seoj17.data.repository.match.MatchRepository
import io.github.seoj17.domain.model.SummonerMatchInfoDomainModel
import io.github.seoj17.domain.usecase.match.GetMatchUseCase
import javax.inject.Inject

@Reusable
class GetSummonerUseCase @Inject constructor(
    private val repository: MatchRepository,
    private val getMatchUseCase: GetMatchUseCase,
) {
    suspend operator fun invoke(
        puuid: String,
        start: Int = 0,
    ): Result<List<SummonerMatchInfoDomainModel>> {
        return runCatching {
            repository
                .getMatchId(puuid, start)
                .map { matchId ->
                    val participateResponse = getMatchUseCase(matchId)
                        .getOrThrow()
                        .info
                        .participants
                        .single { it.puuid == puuid }
                    
                    SummonerMatchInfoDomainModel(participateResponse)
                }
        }
    }
}
