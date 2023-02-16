package io.github.seoj17.canyongg.ui.detail.summaryTab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.domain.GetParticipantsMatches
import io.github.seoj17.canyongg.domain.GetUserTierUseCase
import io.github.seoj17.canyongg.ui.model.ParticipantsMatches
import io.github.seoj17.canyongg.ui.model.TeamKdaInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchSummaryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getUserTierUseCase: GetUserTierUseCase,
    private val getParticipantsMatches: GetParticipantsMatches,
) : ViewModel() {

    private val matchId = MatchSummaryFragmentArgs.fromSavedStateHandle(savedStateHandle).matchId

    private val _winTeamKdaInfo = MutableLiveData<TeamKdaInfo>()
    val winTeamKdaInfo: LiveData<TeamKdaInfo> = _winTeamKdaInfo

    private val _loseTeamKdaInfo = MutableLiveData<TeamKdaInfo>()
    val loseTeamKdaInfo: LiveData<TeamKdaInfo> = _loseTeamKdaInfo

    private val _winTeam = MutableLiveData<List<ParticipantsMatches>>()
    val winTeam: LiveData<List<ParticipantsMatches>> = _winTeam

    private val _loseTeam = MutableLiveData<List<ParticipantsMatches>>()
    val loseTeam: LiveData<List<ParticipantsMatches>> = _loseTeam

    init {
        if (matchId.isNotBlank()) {
            viewModelScope.launch {
                val winInfoList = mutableListOf<ParticipantsMatches>()
                val loseInfoList = mutableListOf<ParticipantsMatches>()
                var winKills = 0
                var loseKills = 0
                var winAssists = 0
                var loseAssists = 0
                var winDeaths = 0
                var loseDeaths = 0
                var playedTime = 0

                getParticipantsMatches(matchId).forEach { matchInfo ->
                    val rank = getUserTierUseCase(matchInfo.summonerId)?.tier ?: "UNRANKED"
                    val info = ParticipantsMatches(matchInfo, rank)
                    playedTime = matchInfo.timePlayed

                    if (matchInfo.win) {
                        winInfoList.add(info)
                        winKills += info.kills
                        winAssists += info.assists
                        winDeaths += info.deaths
                    } else {
                        loseInfoList.add(info)
                        loseKills += info.kills
                        loseAssists += info.assists
                        loseDeaths += info.deaths
                    }
                }

                _winTeam.value = winInfoList
                _loseTeam.value = loseInfoList

                _winTeamKdaInfo.value =
                    TeamKdaInfo(winKills, winDeaths, winAssists, true, playedTime)
                _loseTeamKdaInfo.value =
                    TeamKdaInfo(loseKills, loseDeaths, loseAssists, false, playedTime)
            }
        }
    }

}