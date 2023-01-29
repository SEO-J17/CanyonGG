package io.github.seoj17.canyongg.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.data.remote.response.Participant
import io.github.seoj17.canyongg.data.repository.InfoRepository
import io.github.seoj17.canyongg.utils.Event
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val repository: InfoRepository,
) : ViewModel() {

    private val userName =
        MainFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerName ?: ""

    private val _userInfo = MutableLiveData<Summoner?>()
    val userInfo: LiveData<Summoner?> = _userInfo

    private val _userTier = MutableLiveData<String>("UNRANKED")
    val userTier: LiveData<String> = _userTier

    private val _userKda = MutableLiveData<Double>(0.0)
    val userKda: LiveData<Double> = _userKda

    private val _userWinScore = MutableLiveData<Int>(0)
    val userWinScore: LiveData<Int> = _userWinScore

    private val _userWinCnt = MutableLiveData<Int>(0)
    val userWinCnt: LiveData<Int> = _userWinCnt

    private val _userLoseCnt = MutableLiveData<Int>(0)
    val userLoseCnt: LiveData<Int> = _userLoseCnt

    private val _champWinScore = MutableLiveData<MutableMap<String, Int>>()
    val champWinScore: LiveData<MutableMap<String, Int>> = _champWinScore

    private val _champKda = MutableLiveData<MutableMap<String, Double>>()
    val champKda: LiveData<MutableMap<String, Double>> = _champKda

    private val _mostChampList = MutableLiveData<MutableList<String>>()
    val mostChampList: LiveData<MutableList<String>> = _mostChampList

    private val _errorEvent = MutableLiveData<Event<Boolean>>()
    val errorEvent: LiveData<Event<Boolean>> = _errorEvent

    private val champCntMap = mutableMapOf<String, Int>()
    private val champKillMap = mutableMapOf<String, Int>()
    private val champDeathMap = mutableMapOf<String, Int>()
    private val champWinCntMap = mutableMapOf<String, Int>()


    suspend fun fetchUserInfo() {
        viewModelScope.async {
            _userInfo.value = repository.getSummonerInfo("ㅗ")
        }.await().also {
            fetchScoreData()
        }
    }

    private suspend fun fetchScoreData() {
        _userInfo.value?.let { user ->
            viewModelScope.launch {
                repository.getTier(user.id).also {
                    if (it != null) {
                        _userTier.value = (it.tier + " " + it.rank)
                    }
                }
            }

            val matchIdList = viewModelScope.async {
                repository.getMatchInfo(user.puuid, 0)
            }.await()

            matchIdList.map { matchInfo ->
                matchInfo.info.participants.find { participant ->
                    participant.puuid == user.puuid
                }.also {
                    if (it != null) {
                        calcScore(it)
                    }
                }
            }
            calcWinScore()
            calcMostChamp()
            calcKda()
        } ?:run {
            _errorEvent.value = Event(true)
        }
    }

    private fun calcScore(participant: Participant) {
        val champ = participant.championName
        val kill = participant.assists + participant.kills
        val isWin = participant.win

        champCntMap[champ] = champCntMap.getOrDefault(champ, 0) + 1
        champKillMap[champ] = champKillMap.getOrDefault(champ, 0) + kill
        champDeathMap[champ] = champDeathMap.getOrDefault(champ, 0) + participant.deaths
        if (isWin) {
            champWinCntMap[champ] = champWinCntMap.getOrDefault(champ, 0) + 1
        }
    }

    // 승률 계산 함수
    private fun calcWinScore() {
        // 총 몇 번 이겼는지
        val winCnt = champWinCntMap.values.sum()
        val wholeMatches = champCntMap.values.sum()

        _champWinScore.value = mutableMapOf()
        champWinCntMap.forEach { (champ, value) ->
            _champWinScore.value?.let { map ->
                map[champ] =
                    value * 100 / champCntMap.getOrDefault(champ, 0)
            }
        }

        _userWinCnt.value = winCnt
        _userLoseCnt.value = wholeMatches - winCnt
        _userWinScore.value = (winCnt * 100) / wholeMatches
    }

    private fun calcMostChamp() {
        _mostChampList.value = mutableListOf()
        champCntMap
            .toList()
            // 플레이 한 경기 수로 정렬뒤 사전 순으로 정렬
            .sortedWith(compareByDescending<Pair<String, Int>> { it.second }.thenBy { it.first })
            .toMap()
            .run {
                var cnt = 0
                forEach breaker@{
                    if (cnt == 3) {
                        return@breaker
                    }
                    _mostChampList.value?.add(it.key)
                    cnt++
                }
            }
    }

    private fun calcKda() {
        _userKda.value = champKillMap.values.sum() / champDeathMap.values.sum().toDouble()
        _champKda.value = mutableMapOf()
        _mostChampList.value?.forEach { champ ->
            champKillMap[champ]?.div(
                if (champDeathMap[champ] == 0) {
                    1.0
                } else {
                    champDeathMap[champ]!!.toDouble()
                }
            )?.let { kda ->
                _champKda.value?.put(champ, kda)
            }
        }
    }
}


