package io.github.seoj17.canyongg.ui.detail.rankTab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.domain.GetParticipantsMatches
import io.github.seoj17.canyongg.ui.model.MyRank
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyRankViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getParticipantsMatches: GetParticipantsMatches,
) : ViewModel() {

    private val matchId = MyRankFragmentArgs.fromSavedStateHandle(savedStateHandle).matchId
    private val puuid = MyRankFragmentArgs.fromSavedStateHandle(savedStateHandle).puuid

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

    init {
        viewModelScope.launch {
            val summonerMatches = getParticipantsMatches(matchId).map {
                if (it.puuid == puuid) {
                    _summonerName.value = it.summonerName
                }
                SummonerMatchRecord(it)
            }
            _dealtRank.value =
                getSummonerRank(
                    summonerMatches.map { it.totalDealt }.sortedByDescending { it },
                    summonerMatches.find { it.puuid == puuid }?.totalDealt,
                )

            _damagedRank.value =
                getSummonerRank(
                    summonerMatches.map { it.totalDamaged }.sortedByDescending { it },
                    summonerMatches.find { it.puuid == puuid }?.totalDamaged,
                )

            _killRank.value =
                getSummonerRank(
                    summonerMatches.map { it.kill }.sortedByDescending { it },
                    summonerMatches.find { it.puuid == puuid }?.kill,
                )

            _deathRank.value =
                getSummonerRank(
                    summonerMatches.map { it.death }.sortedByDescending { it },
                    summonerMatches.find { it.puuid == puuid }?.death,
                )

            _assistRank.value =
                getSummonerRank(
                    summonerMatches.map { it.assist }.sortedByDescending { it },
                    summonerMatches.find { it.puuid == puuid }?.assist,
                )

            _minionRank.value =
                getSummonerRank(
                    summonerMatches.map { it.minions }.sortedByDescending { it },
                    summonerMatches.find { it.puuid == puuid }?.minions,
                )

            _spentGoldRank.value =
                getSummonerRank(
                    summonerMatches.map { it.spentGold }.sortedByDescending { it },
                    summonerMatches.find { it.puuid == puuid }?.spentGold,
                )

            _visionScoreRank.value =
                getSummonerRank(
                    summonerMatches.map { it.visionScore }.sortedByDescending { it },
                    summonerMatches.find { it.puuid == puuid }?.visionScore,
                )
        }
    }

    private fun getSummonerRank(matches: List<Int>, value: Int?): MyRank {
        return MyRank(
            puuid,
            value ?: 0,
            matches.max(),
            matches.indexOf(value) + 1,
        )
    }
}