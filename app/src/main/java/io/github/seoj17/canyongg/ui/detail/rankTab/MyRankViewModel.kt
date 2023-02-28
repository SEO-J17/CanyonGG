package io.github.seoj17.canyongg.ui.detail.rankTab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.domain.usecase.match.GetParticipantsMatchesUseCase
import io.github.seoj17.canyongg.ui.model.MyRank
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRankViewModel @Inject constructor(
    private val getParticipantsMatches: GetParticipantsMatchesUseCase,
) : ViewModel() {
    private val _summonerName = MutableLiveData<String>()
    val summonerName: LiveData<String> = _summonerName

    private val _dealtRank = MutableLiveData<MyRank>()
    val dealtRank: LiveData<MyRank> = _dealtRank

    private val _damagedRank = MutableLiveData<MyRank>()
    val damagedRank: LiveData<MyRank> = _damagedRank

    private val _killRank = MutableLiveData<MyRank>()
    val killRank: LiveData<MyRank> = _killRank

    private val _deathRank = MutableLiveData<MyRank>()
    val deathRank: LiveData<MyRank> = _deathRank

    private val _assistRank = MutableLiveData<MyRank>()
    val assistRank: LiveData<MyRank> = _assistRank

    private val _minionRank = MutableLiveData<MyRank>()
    val minionRank: LiveData<MyRank> = _minionRank

    private val _spentGoldRank = MutableLiveData<MyRank>()
    val spentGoldRank: LiveData<MyRank> = _spentGoldRank

    private val _visionScoreRank = MutableLiveData<MyRank>()
    val visionScoreRank: LiveData<MyRank> = _visionScoreRank

    private val _matchId = MutableLiveData<String>()
    val matchId: LiveData<String> = _matchId

    private val _puuid = MutableLiveData<String>()
    val puuid: LiveData<String> = _puuid

    fun fetch() {
        _matchId.value?.let { matchId ->
            viewModelScope.launch {
                val summonerMatches = getParticipantsMatches(matchId).map {
                    if (it.puuid == puuid.value) {
                        _summonerName.value = it.summonerName
                    }
                    SummonerMatchRecord(it)
                }
                _dealtRank.value =
                    getSummonerRank(
                        summonerMatches.map { it.totalDealt }.sortedByDescending { it },
                        summonerMatches.find { it.puuid == _puuid.value }?.totalDealt,
                    )

                _damagedRank.value =
                    getSummonerRank(
                        summonerMatches.map { it.totalDamaged }.sortedByDescending { it },
                        summonerMatches.find { it.puuid == _puuid.value }?.totalDamaged,
                    )

                _killRank.value =
                    getSummonerRank(
                        summonerMatches.map { it.kill }.sortedByDescending { it },
                        summonerMatches.find { it.puuid == _puuid.value }?.kill,
                    )

                _deathRank.value =
                    getSummonerRank(
                        summonerMatches.map { it.death }.sortedByDescending { it },
                        summonerMatches.find { it.puuid == _puuid.value }?.death,
                    )

                _assistRank.value =
                    getSummonerRank(
                        summonerMatches.map { it.assist }.sortedByDescending { it },
                        summonerMatches.find { it.puuid == _puuid.value }?.assist,
                    )

                _minionRank.value =
                    getSummonerRank(
                        summonerMatches.map { it.minions }.sortedByDescending { it },
                        summonerMatches.find { it.puuid == _puuid.value }?.minions,
                    )

                _spentGoldRank.value =
                    getSummonerRank(
                        summonerMatches.map { it.spentGold }.sortedByDescending { it },
                        summonerMatches.find { it.puuid == _puuid.value }?.spentGold,
                    )

                _visionScoreRank.value =
                    getSummonerRank(
                        summonerMatches.map { it.visionScore }.sortedByDescending { it },
                        summonerMatches.find { it.puuid == _puuid.value }?.visionScore,
                    )
            }
        }
    }

    private fun getSummonerRank(matches: List<Int>, value: Int?): MyRank {
        return MyRank(
            _puuid.value ?: "",
            value ?: 0,
            matches.max(),
            matches.indexOf(value) + 1,
        )
    }

    fun setMatchId(matchId: String) {
        _matchId.value = matchId
    }

    fun setSummonerPuuid(puuid: String) {
        _puuid.value = puuid
    }
}