package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.DataCenterRepository
import io.github.seoj17.canyongg.data.repository.MatchesRepository
import io.github.seoj17.canyongg.domain.model.DomainSummonerMatchInfo
import javax.inject.Inject

@Reusable
class GetParticipantsMatches @Inject constructor(
    private val matchRepository: MatchesRepository,
    private val dataCenterRepository: DataCenterRepository,
    ) {
    suspend operator fun invoke(matchId: String): List<DomainSummonerMatchInfo> {
        return matchRepository
            .getMatchInfo(matchId)
            .info
            .participants
            .map { response ->
                DomainSummonerMatchInfo(
                    response,
                    dataCenterRepository.getSpell(response.summoner1Id),
                    dataCenterRepository.getSpell(response.summoner2Id),
                    dataCenterRepository.getMainPerk(
                        response.perks.styles[0].style,
                        response.perks.styles[0].selections[0].perk
                    ),
                    dataCenterRepository.getSubPerk(response.perks.styles[1].style)
                )
            }
    }
}