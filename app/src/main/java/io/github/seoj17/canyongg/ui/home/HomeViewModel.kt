package io.github.seoj17.canyongg.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.data.model.MainMyInfo
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.domain.GetMyMatchUseCase
import io.github.seoj17.canyongg.domain.GetUserInfoUseCase
import io.github.seoj17.canyongg.domain.GetUserTierUseCase
import io.github.seoj17.canyongg.ui.model.ChampInfo
import io.github.seoj17.canyongg.ui.model.UserRecord
import io.github.seoj17.canyongg.utils.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserTierUseCase: GetUserTierUseCase,
    private val getMyMatchUseCase: GetMyMatchUseCase,
) : ViewModel() {

    private val userName =
        HomeFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerName ?: ""

    private val _userInfo = MutableLiveData<Summoner?>()
    val userInfo: LiveData<Summoner?> = _userInfo

    private val _userTier = MutableLiveData<String>("UNRANKED")
    val userTier: LiveData<String> = _userTier

    private val _userRecord = MutableLiveData<UserRecord>()
    val userRecord: LiveData<UserRecord> = _userRecord

    private val _mostChampList = MutableLiveData<MutableList<ChampInfo>>()
    val mostChampList: LiveData<MutableList<ChampInfo>> = _mostChampList

    private val _errorEvent = MutableLiveData<Event<Boolean>>()
    val errorEvent: LiveData<Event<Boolean>> = _errorEvent


    fun fetchUserInfo() {
        viewModelScope.launch {
            val summonerInfo = getUserInfoUseCase("Hide on bush")
            summonerInfo?.let { summoner ->
                _userInfo.value = summoner

                getUserTierUseCase(summoner.id)?.let { userTier ->
                    _userTier.value = "${userTier.tier} ${userTier.rank}"
                }

                val myMatches = getMyMatchUseCase(summoner.puuid)
                if (myMatches.isNotEmpty()) {
                    calcUserInfo(myMatches)
                    calcMostChampion(myMatches)
                }

            } ?: run {
                _errorEvent.value = Event(true)
            }
        }
    }

    private fun calcUserInfo(myMatches: List<MainMyInfo>) {
        val wholeMatch = myMatches.size
        val realMatch = wholeMatch - myMatches.count { it.gameEndedInEarlySurrender }
        val kills = myMatches.sumOf { it.kills } + myMatches.sumOf { it.assists }

        val win = myMatches.count { it.win && !it.gameEndedInEarlySurrender }
        val lose = realMatch - win
        val winRate = (win * 100) / realMatch
        val kda = kills / myMatches.sumOf { it.deaths }.toDouble()

        _userRecord.value = UserRecord(wholeMatch, win, lose, winRate, kda)
    }

    private fun calcMostChampion(myMatches: List<MainMyInfo>) {
        val champWinCntMap = mutableMapOf<String, Int>()
        val champKillMap = mutableMapOf<String, Int>()
        val champDeathMap = mutableMapOf<String, Int>()
        //가장 많이 플레이 한 챔피언 3개
        val mostChampsMap =
            myMatches
                .filter { !it.gameEndedInEarlySurrender }
                .groupingBy { it.championName }
                .eachCount()
                .toList()
                .sortedWith(
                    // 챔피언 별로 몇번 플레이 했는지 내림차순 정렬, 플레이 수가 같으면 이름 정렬.
                    compareBy({ -it.second }, { it.first })
                )
                .take(3)
                .toMap()

        myMatches.forEach { myInfo ->
            val name = myInfo.championName
            if (mostChampsMap.containsKey(name)) {
                champDeathMap[name] = champDeathMap.getOrElse(name) { 0 } + myInfo.deaths
                champKillMap[name] =
                    champKillMap.getOrElse(name) { 0 } + myInfo.kills + myInfo.assists
                if (myInfo.win) {
                    champWinCntMap[name] = champWinCntMap.getOrElse(name) { 0 } + 1
                }
            }
        }

        val infoList = mutableListOf<ChampInfo>()
        mostChampsMap.forEach { (champ, playCnt) ->
            val kills = champKillMap.getOrElse(champ) { 0 }
            val deaths = champDeathMap.getOrElse(champ) { 1 }
            val kda = kills / deaths.toDouble()
            val winRate = 100 / playCnt * champWinCntMap.getOrElse(champ) { 0 }

            infoList.add(ChampInfo(champ, winRate, kda))
        }
        _mostChampList.value = infoList
    }
}


