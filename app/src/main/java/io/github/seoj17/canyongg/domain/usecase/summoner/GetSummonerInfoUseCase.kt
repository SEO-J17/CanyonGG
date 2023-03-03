package io.github.seoj17.canyongg.domain.usecase.summoner

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.SummonerInfoRepository
import io.github.seoj17.canyongg.domain.model.SummonerInfoDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetSummonerInfoUseCase @Inject constructor(
    private val summonerInfoRepository: SummonerInfoRepository,
) {
    operator fun invoke(puuid: String): Flow<SummonerInfoDomainModel?> {
        return summonerInfoRepository
            .getSummonerInfo(puuid)
            .map {
                it?.let { data ->
                    SummonerInfoDomainModel(data)
                }
            }
    }
}