package io.github.seoj17.domain.usecase.match

import dagger.Reusable
import io.github.seoj17.data.repository.DataCenterRepository
import io.github.seoj17.data.repository.MatchRepository
import io.github.seoj17.data.repository.PerkRepository
import io.github.seoj17.domain.model.MatchInfoDomainModel
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
                    val firstSpell = async {
                        dataCenterRepository.getSpell(entity.firstSpell)
                    }
                    val secondSpell = async {
                        dataCenterRepository.getSpell(entity.secondSpell)
                    }
                    val mainRune = async {
                        perksRepository.getPerk(entity.mainRune)
                    }
                    val subRune = async {
                        perksRepository.getPerk(entity.subRune)
                    }
                    MatchInfoDomainModel(
                        entity,
                        firstSpell.await(),
                        secondSpell.await(),
                        mainRune.await().imgUrl,
                        subRune.await().imgUrl,
                    )
                }
            }
    }
}
