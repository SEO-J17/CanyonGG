package io.github.seoj17.canyongg.domain.usecase.summoner

import androidx.paging.PagingData
import androidx.paging.map
import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.DataCenterRepository
import io.github.seoj17.canyongg.data.repository.MatchRepository
import io.github.seoj17.canyongg.data.repository.PerkRepository
import io.github.seoj17.canyongg.domain.model.MatchInfoDomainModel
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
                        MatchInfoDomainModel(
                            data,
                            async {
                                dataCenterRepository.getSpell(data.firstSpell)
                            }.await(),
                            async {
                                dataCenterRepository.getSpell(data.secondSpell)
                            }.await(),
                            async{
                                perksRepository.getPerk(data.mainRune)
                            }.await().imgUrl,
                            async{
                                perksRepository.getPerk(data.subRune)
                            }.await().imgUrl
                        )
                    }
                }
            }
    }
}
