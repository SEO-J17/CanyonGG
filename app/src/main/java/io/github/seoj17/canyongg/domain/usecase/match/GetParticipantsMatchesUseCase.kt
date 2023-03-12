package io.github.seoj17.canyongg.domain.usecase.match

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.DataCenterRepository
import io.github.seoj17.canyongg.data.repository.MatchRepository
import io.github.seoj17.canyongg.data.repository.PerkRepository
import io.github.seoj17.canyongg.domain.model.MatchInfoDomainModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

@Reusable
class GetParticipantsMatchesUseCase @Inject constructor(
    private val matchRepository: MatchRepository,
    private val dataCenterRepository: DataCenterRepository,
    private val perksRepository: PerkRepository,
) {
    suspend operator fun invoke(matchId: String): List<MatchInfoDomainModel> {
        return matchRepository
            .getParticipantsMatchInfo(matchId)
            .map { entity ->
                coroutineScope {
                    MatchInfoDomainModel(
                        entity,
                        async {
                            dataCenterRepository.getSpell(entity.firstSpell)
                        }.await(),
                        async {
                            dataCenterRepository.getSpell(entity.secondSpell)
                        }.await(),
                        async {
                            perksRepository.getPerk(entity.mainRune)
                        }.await().imgUrl,
                        async {
                            perksRepository.getPerk(entity.subRune)
                        }.await().imgUrl,
                    )
                }
            }
    }
}