package io.github.seoj17.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.domain.model.ChampInfoDomainModel
import io.github.seoj17.domain.usecase.match.GetRegisterUserMatchListUseCase
import javax.inject.Inject

@Reusable
class GetMostChampionListUseCase @Inject constructor(
    private val getRegisterUserMatchListUseCase: GetRegisterUserMatchListUseCase,
) {
    suspend operator fun invoke(puuid: String): List<ChampInfoDomainModel> {
        getRegisterUserMatchListUseCase(puuid)
            .filterNotNull()
            .run {
                val champWinCntMap = mutableMapOf<String, Int>()
                val champKillMap = mutableMapOf<String, Int>()
                val champDeathMap = mutableMapOf<String, Int>()
                // 가장 많이 플레이 한 챔피언 3개
                val mostChampsMap =
                    filter { !it.gameEndedInEarlySurrender }
                        .groupingBy { it.championName }
                        .eachCount()
                        .toList()
                        .sortedWith(
                            // 챔피언 별로 몇번 플레이 했는지 내림차순 정렬, 플레이 수가 같으면 이름 정렬.
                            compareBy({ -it.second }, { it.first }),
                        )
                        .take(3)
                        .toMap()

                forEach { myInfo ->
                    val name = myInfo.championName
                    if (mostChampsMap.containsKey(name)) {
                        champDeathMap[name] = champDeathMap.getOrDefault(name, 0) + myInfo.deaths
                        champKillMap[name] =
                            champKillMap.getOrDefault(name, 0) + myInfo.kills + myInfo.assists
                        if (myInfo.win) {
                            champWinCntMap[name] = champWinCntMap.getOrDefault(name, 0) + 1
                        }
                    }
                }

                val infoList = mutableListOf<ChampInfoDomainModel>()
                mostChampsMap.forEach { (champ, playCnt) ->
                    val kills = champKillMap.getOrDefault(champ, 0)
                    val deaths = champDeathMap.getOrDefault(champ, 1)
                    val kda = kills / deaths.toDouble()
                    val winRate = (champWinCntMap.getOrDefault(champ, 0) * 100) / playCnt

                    infoList.add(ChampInfoDomainModel(champ, winRate, kda))
                }
                return infoList.toList()
            }
    }
}
