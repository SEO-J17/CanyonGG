package io.github.seoj17.canyongg.domain.usecase.summoner

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.MatchRepository
import io.github.seoj17.canyongg.domain.model.SummonerMatchInfoDomainModel
import io.github.seoj17.canyongg.domain.usecase.match.GetMatchUseCase
import javax.inject.Inject

@Reusable
class GetSummonerUseCase @Inject constructor(
    private val repository: MatchRepository,
    private val getMatchUseCase: GetMatchUseCase,
) {
    suspend operator fun invoke(
        puuid: String,
        start: Int = 0,
    ): List<SummonerMatchInfoDomainModel?> {
        return repository.getMatchId(puuid, start).map { matchId ->
            getMatchUseCase(matchId)
                .info
                .participants
                .find { it.puuid == puuid }
                ?.let {
                    SummonerMatchInfoDomainModel(it)
                }
        }
    }
}