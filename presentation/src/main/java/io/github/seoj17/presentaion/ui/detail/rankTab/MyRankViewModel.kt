package io.github.seoj17.presentaion.ui.detail.rankTab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.match.GetParticipantsMatchesUseCase
import io.github.seoj17.presentaion.model.MyRank
import io.github.seoj17.presentaion.model.SummonerMatchRecord
import javax.inject.Inject

@HiltViewModel
class MyRankViewModel @Inject constructor(
    private val getParticipantsMatches: GetParticipantsMatchesUseCase,
) : ViewModel() {

    private val _matchId = MutableLiveData<String>()
    val matchId: LiveData<String> = _matchId

    private val _puuid = MutableLiveData<String>()
    val puuid: LiveData<String> = _puuid

    private val _summonerName = MutableLiveData<String>()
    val summonerName: LiveData<String> = _summonerName

    val participantsMatches = matchId.switchMap { matchId ->
        liveData {
            emit(
                getParticipantsMatches(matchId).map {
                    if (it.puuid == puuid.value) {
                        _summonerName.value = it.summonerName
                    }
                    SummonerMatchRecord(it)
                },
            )
        }
    }

    val dealtRank: LiveData<MyRank> = participantsMatches.map { matchList ->
        getSummonerRank(
            matchList.map { it.totalDealt }.sortedByDescending { it },
            matchList.find { it.puuid == puuid.value }?.totalDealt,
        )
    }

    val damagedRank: LiveData<MyRank> = participantsMatches.map { matchList ->
        getSummonerRank(
            matchList.map { it.totalDamaged }.sortedByDescending { it },
            matchList.find { it.puuid == puuid.value }?.totalDamaged,
        )
    }

    val killRank: LiveData<MyRank> = participantsMatches.map { matchList ->
        getSummonerRank(
            matchList.map { it.kill }.sortedByDescending { it },
            matchList.find { it.puuid == puuid.value }?.kill,
        )
    }

    val deathRank: LiveData<MyRank> = participantsMatches.map { matchList ->
        getSummonerRank(
            matchList.map { it.death }.sortedByDescending { it },
            matchList.find { it.puuid == puuid.value }?.death,
        )
    }

    val assistRank: LiveData<MyRank> = participantsMatches.map { matchList ->
        getSummonerRank(
            matchList.map { it.assist }.sortedByDescending { it },
            matchList.find { it.puuid == puuid.value }?.assist,
        )
    }

    val minionRank: LiveData<MyRank> = participantsMatches.map { matchList ->
        getSummonerRank(
            matchList.map { it.minions }.sortedByDescending { it },
            matchList.find { it.puuid == puuid.value }?.minions,
        )
    }

    val spentGoldRank: LiveData<MyRank> = participantsMatches.map { matchList ->
        getSummonerRank(
            matchList.map { it.spentGold }.sortedByDescending { it },
            matchList.find { it.puuid == puuid.value }?.spentGold,
        )
    }

    val visionScoreRank: LiveData<MyRank> = participantsMatches.map { matchList ->
        getSummonerRank(
            matchList.map { it.visionScore }.sortedByDescending { it },
            matchList.find { it.puuid == puuid.value }?.visionScore,
        )
    }

    private fun getSummonerRank(matches: List<Int>, value: Int?): MyRank {
        return MyRank(
            puuid.value,
            value,
            matches.maxOrNull(),
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
