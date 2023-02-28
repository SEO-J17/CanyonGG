package io.github.seoj17.canyongg.domain.usecase.match

import dagger.Reusable
import io.github.seoj17.canyongg.data.model.MainMyInfo
import io.github.seoj17.canyongg.data.repository.MatchesRepository
import javax.inject.Inject

@Reusable
class GetMyMatchUseCase @Inject constructor(
    private val repository: MatchesRepository,
) {
    suspend operator fun invoke(puuid: String, start: Int = 0): List<MainMyInfo> {
        val myInfoList = mutableListOf<MainMyInfo>()
        repository
            .getMatchInfo(puuid, start)
            .forEach { matchInfo ->
                matchInfo.info.participants
                    .find {
                        it.puuid == puuid
                    }
                    ?.let {
                        myInfoList.add(MainMyInfo(it))
                    }
            }
        return myInfoList
    }
}