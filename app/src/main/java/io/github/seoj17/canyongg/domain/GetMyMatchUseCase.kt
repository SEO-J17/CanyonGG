package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.model.MainMyInfo
import io.github.seoj17.canyongg.data.repository.InfoRepository
import javax.inject.Inject

@Reusable
class GetMyMatchUseCase @Inject constructor(
    private val repository: InfoRepository
) {
    suspend operator fun invoke(puuid: String, start: Int = 0): List<MainMyInfo> {
        val myInfoList = mutableListOf<MainMyInfo>()
        repository.getMatchInfo(puuid, start).map { matchInfo ->
            val myMatch = matchInfo.info.participants.find {
                it.puuid == puuid
            }
            myMatch?.let {
                myInfoList.add(MainMyInfo(it))
            }
        }
        return myInfoList
    }
}