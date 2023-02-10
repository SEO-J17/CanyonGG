package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.MatchesRepository
import io.github.seoj17.canyongg.domain.model.DomainSummonerMatchInfo
import javax.inject.Inject

@Reusable
class GetSummonerUseCase @Inject constructor(
    private val repository: MatchesRepository
) {
    suspend operator fun invoke(
        puuid: String,
        start: Int = 0,
    ): List<DomainSummonerMatchInfo> {
        val myInfoList = mutableListOf<DomainSummonerMatchInfo>()
        repository
            .getMatchInfo(puuid, start)
            .forEach { matchInfo ->
                matchInfo.info.participants
                    .find {
                        it.puuid == puuid
                    }
                    ?.let {
                        myInfoList.add(DomainSummonerMatchInfo(it))
                    }
            }
        return myInfoList
    }
}