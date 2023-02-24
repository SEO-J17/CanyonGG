package io.github.seoj17.canyongg.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.data.model.MainMyInfo
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.domain.AddMyMostChampsUseCase
import io.github.seoj17.canyongg.domain.AddMyUserInfoUseCase
import io.github.seoj17.canyongg.domain.AddSummonerInfoUseCase
import io.github.seoj17.canyongg.domain.DeleteBookmarkSummonerUseCase
import io.github.seoj17.canyongg.domain.DeleteMyUserInfoUseCase
import io.github.seoj17.canyongg.domain.GetBookmarkSummonerUseCase
import io.github.seoj17.canyongg.domain.GetMostChampUseCase
import io.github.seoj17.canyongg.domain.GetMyMatchUseCase
import io.github.seoj17.canyongg.domain.GetMyUserInfoUseCase
import io.github.seoj17.canyongg.domain.GetUserInfoUseCase
import io.github.seoj17.canyongg.domain.GetUserTierUseCase
import io.github.seoj17.canyongg.domain.model.DomainMostChamps
import io.github.seoj17.canyongg.domain.model.DomainMyUserInfo
import io.github.seoj17.canyongg.domain.model.DomainSummonerInfo
import io.github.seoj17.canyongg.ui.model.ChampInfo
import io.github.seoj17.canyongg.ui.model.MyUserInfo
import io.github.seoj17.canyongg.ui.model.UserRecord
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserTierUseCase: GetUserTierUseCase,
    private val getMyMatchUseCase: GetMyMatchUseCase,
    private val getBookmarkSummoner: GetBookmarkSummonerUseCase,
    private val deleteBookmarkSummoner: DeleteBookmarkSummonerUseCase,
    private val getMyUserInfoUseCase: GetMyUserInfoUseCase,
    private val getMostChampUseCase: GetMostChampUseCase,
    private val addMyUserInfo: AddMyUserInfoUseCase,
    private val addMyMostChamps: AddMyMostChampsUseCase,
    private val deleteMyUserInfo: DeleteMyUserInfoUseCase,
    private val addSummonerInfoUseCase: AddSummonerInfoUseCase,
) : ViewModel() {

    private val summonerName =
        HomeFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerName ?: ""

    val userInfo = getMyUserInfoUseCase().asLiveData().map { domain ->
        domain?.let { it -> MyUserInfo(it) }
    }

    private val mostChampsInfo = getMostChampUseCase().asLiveData()

    val firstMostChamp: LiveData<ChampInfo?> = setMostChamps(index = 0)

    val secondMostChamp: LiveData<ChampInfo?> = setMostChamps(index = 1)

    val thirdMostChamp: LiveData<ChampInfo?> = setMostChamps(index = 2)

    val bookmarkSummoners = getBookmarkSummoner().asLiveData()

    init {
        if (summonerName.isNotBlank()) {
            fetchData()
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            getUserInfoUseCase(summonerName)?.let { summoner ->

                val tier = getUserTierUseCase(summoner.id)?.let { userTier ->
                    "${userTier.tier} ${userTier.rank}"
                } ?: "UNRANKED"

                val myMatches = getMyMatchUseCase(summoner.puuid)
                if (myMatches.isNotEmpty()) {
                    insertUserInfoLocal(summoner, calcUserInfo(myMatches), tier)
                    insertMostChampLocal(summoner, calcMostChampion(myMatches))
                }
            }
        }
    }

    private fun calcUserInfo(myMatches: List<MainMyInfo>): UserRecord {
        val wholeMatch = myMatches.size
        val realMatch = wholeMatch - myMatches.count { it.gameEndedInEarlySurrender }
        val kills = myMatches.sumOf { it.kills } + myMatches.sumOf { it.assists }

        val win = myMatches.count { it.win && !it.gameEndedInEarlySurrender }
        val lose = realMatch - win
        val winRate = (win * 100) / realMatch
        val kda = kills / myMatches.sumOf { it.deaths }.toDouble()
        val mostKill = myMatches.maxOf { it.largestKill }

        return UserRecord(wholeMatch, win, lose, winRate, kda, mostKill)
    }

    private fun calcMostChampion(myMatches: List<MainMyInfo>): List<ChampInfo> {
        val champWinCntMap = mutableMapOf<String, Int>()
        val champKillMap = mutableMapOf<String, Int>()
        val champDeathMap = mutableMapOf<String, Int>()
        //가장 많이 플레이 한 챔피언 3개
        val mostChampsMap =
            myMatches.filter { !it.gameEndedInEarlySurrender }.groupingBy { it.championName }
                .eachCount().toList().sortedWith(
                    // 챔피언 별로 몇번 플레이 했는지 내림차순 정렬, 플레이 수가 같으면 이름 정렬.
                    compareBy({ -it.second }, { it.first })
                ).take(3).toMap()

        myMatches.forEach { myInfo ->
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

        val infoList = mutableListOf<ChampInfo>()
        mostChampsMap.forEach { (champ, playCnt) ->
            val kills = champKillMap.getOrDefault(champ, 0)
            val deaths = champDeathMap.getOrDefault(champ, 1)
            val kda = kills / deaths.toDouble()
            val winRate = (champWinCntMap.getOrDefault(champ, 0) * 100) / playCnt

            infoList.add(ChampInfo(champ, winRate, kda))
        }
        return infoList.toList()
    }

    private suspend fun insertUserInfoLocal(
        summoner: Summoner,
        record: UserRecord,
        tier: String,
    ) {
        addMyUserInfo(
            DomainMyUserInfo(
                puuid = summoner.puuid,
                profile = summoner.profileIconId,
                level = summoner.summonerLevel,
                name = summoner.name,
                tier = tier,
                wholeMatch = record.wholeMatch,
                win = record.winCount,
                lose = record.loseCount,
                winRate = record.winRate,
                kda = record.kda,
            )
        )
        addSummonerInfoUseCase(
            DomainSummonerInfo(
                puuid = summoner.puuid,
                profile = summoner.profileIconId,
                level = summoner.summonerLevel,
                name = summoner.name,
                tier = tier,
                wholeMatch = record.wholeMatch,
                win = record.winCount,
                lose = record.loseCount,
                winRate = record.winRate,
                kda = record.kda,
                largestKill = record.largestKill,
            )
        )
    }

    private suspend fun insertMostChampLocal(summoner: Summoner, champs: List<ChampInfo>) {
        addMyMostChamps(
            champs.map {
                DomainMostChamps(
                    champName = it.name,
                    userPuuid = summoner.puuid,
                    champKda = it.kda,
                    champWinRate = it.winRate,
                )
            }
        )
    }

    fun removeBookmark(name: String) {
        viewModelScope.launch {
            deleteBookmarkSummoner(name)
        }
    }

    fun removeMyInfo() {
        viewModelScope.launch {
            deleteMyUserInfo()
        }
    }

    fun refreshMyInfo() {
        viewModelScope.launch {
            fetchData()
        }
    }

    private fun setMostChamps(index: Int): LiveData<ChampInfo?> {
        return mostChampsInfo.map {
            try {
                ChampInfo(
                    name = it[index].champName,
                    kda = it[index].champKda,
                    winRate = it[index].champWinRate,
                )
            } catch (e: IndexOutOfBoundsException) {
                null
            }
        }
    }
}



