package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.DataCenterRepository
import io.github.seoj17.canyongg.data.repository.MatchesRepository
import io.github.seoj17.canyongg.data.repository.PerksRepository
import io.github.seoj17.canyongg.domain.model.DomainMatches
import javax.inject.Inject

@Reusable
class GetParticipantsMatches @Inject constructor(
    private val matchRepository: MatchesRepository,
    private val dataCenterRepository: DataCenterRepository,
    private val perksRepository: PerksRepository,
) {
    suspend operator fun invoke(matchId: String): List<DomainMatches> {
        return matchRepository
            .getParticipantsMatchInfo(matchId)
            .map { entity ->
                DomainMatches(
                    entity,
                    dataCenterRepository.getSpell(entity.firstSpell),
                    dataCenterRepository.getSpell(entity.secondSpell),
                    perksRepository.getPerk(entity.mainRune).imgUrl,
                    perksRepository.getPerk(entity.subRune).imgUrl,
                )
            }
    }
}