package io.github.seoj17.canyongg.domain.usecase.match

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.DataCenterRepository
import io.github.seoj17.canyongg.data.repository.MatchRepository
import io.github.seoj17.canyongg.data.repository.PerkRepository
import io.github.seoj17.canyongg.domain.model.MatchInfoDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
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
                        withContext(Dispatchers.Default) {
                            dataCenterRepository.getSpell(entity.firstSpell)
                        },
                        withContext(Dispatchers.Default) {
                            dataCenterRepository.getSpell(entity.secondSpell)
                        },
                        withContext(Dispatchers.Default) {
                            perksRepository.getPerk(entity.mainRune)
                        }.imgUrl,
                        withContext(Dispatchers.Default) {
                            perksRepository.getPerk(entity.subRune)
                        }.imgUrl,
                    )
                }
            }
    }
}