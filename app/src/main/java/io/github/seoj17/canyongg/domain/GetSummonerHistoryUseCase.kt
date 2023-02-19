package io.github.seoj17.canyongg.domain

import androidx.paging.PagingData
import androidx.paging.map
import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.ChampionsRepository
import io.github.seoj17.canyongg.data.repository.DataCenterRepository
import io.github.seoj17.canyongg.data.repository.MatchesRepository
import io.github.seoj17.canyongg.data.repository.PerksRepository
import io.github.seoj17.canyongg.domain.model.DomainMatches
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetSummonerHistoryUseCase @Inject constructor(
    private val matchRepository: MatchesRepository,
    private val dataCenterRepository: DataCenterRepository,
    private val perksRepository: PerksRepository,
    private val championsRepository: ChampionsRepository,
) {
    operator fun invoke(puuid: String): Flow<PagingData<DomainMatches>> {
        return matchRepository
            .getMatches(puuid)
            .flow
            .map { paging ->
                paging.map { data ->
                    DomainMatches(
                        data,
                        dataCenterRepository.getSpell(data.summoner1Id),
                        dataCenterRepository.getSpell(data.summoner2Id),
                        perksRepository.getPerk(data.perks.styles[0].selections[0].perk).imgUrl,
                        perksRepository.getPerk(data.perks.styles[1].style).imgUrl,
                        championsRepository.getChampion(data.championId),
                    )
                }
            }
    }
}
