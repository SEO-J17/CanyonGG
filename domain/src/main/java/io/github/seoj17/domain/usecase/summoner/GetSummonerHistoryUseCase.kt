package io.github.seoj17.domain.usecase.summoner

import androidx.paging.PagingData
import androidx.paging.map
import dagger.Reusable
import io.github.seoj17.data.repository.datacenter.DataCenterRepository
import io.github.seoj17.data.repository.match.MatchRepository
import io.github.seoj17.data.repository.perk.PerkRepository
import io.github.seoj17.domain.model.MatchInfoDomainModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetSummonerHistoryUseCase @Inject constructor(
    private val matchRepository: MatchRepository,
    private val dataCenterRepository: DataCenterRepository,
    private val perksRepository: PerkRepository,
) {
    operator fun invoke(puuid: String): Flow<PagingData<MatchInfoDomainModel>> {
        return matchRepository
            .getMatches(puuid)
            .flow
            .map { paging ->
                paging.map { data ->
                    coroutineScope {
                        val firstSpell = async {
                            dataCenterRepository.getSpell(data.firstSpell)
                        }
                        val secondSpell = async {
                            dataCenterRepository.getSpell(data.secondSpell)
                        }
                        val mainRune = async {
                            perksRepository.getPerk(data.mainRune)
                        }
                        val subRune = async {
                            perksRepository.getPerk(data.subRune)
                        }
                        MatchInfoDomainModel(
                            data,
                            firstSpell.await(),
                            secondSpell.await(),
                            mainRune.await().imgUrl,
                            subRune.await().imgUrl,
                        )
                    }
                }
            }
    }
}
