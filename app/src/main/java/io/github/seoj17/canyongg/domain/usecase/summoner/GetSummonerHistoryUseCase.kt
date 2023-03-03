package io.github.seoj17.canyongg.domain.usecase.summoner

import androidx.paging.PagingData
import androidx.paging.map
import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.DataCenterRepository
import io.github.seoj17.canyongg.data.repository.MatchRepository
import io.github.seoj17.canyongg.data.repository.PerkRepository
import io.github.seoj17.canyongg.domain.model.MatchInfoDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
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
                            withContext(Dispatchers.Default) {
                                dataCenterRepository.getSpell(data.firstSpell)
                            },
                            withContext(Dispatchers.Default) {
                                dataCenterRepository.getSpell(data.secondSpell)
                            },
                            withContext(Dispatchers.Default) {
                                perksRepository.getPerk(data.mainRune)
                            }.imgUrl,
                            withContext(Dispatchers.Default) {
                                perksRepository.getPerk(data.subRune)
                            }.imgUrl
                        )
                    }
                }
            }
    }
}
