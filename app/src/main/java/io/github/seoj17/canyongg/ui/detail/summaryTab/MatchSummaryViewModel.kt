package io.github.seoj17.canyongg.ui.detail.summaryTab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.domain.GetParticipantsMatches
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import io.github.seoj17.canyongg.ui.model.TeamKdaInfo
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MatchSummaryViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getParticipantsMatches: GetParticipantsMatches,
) : ViewModel() {

    private val matchId = MatchSummaryFragmentArgs.fromSavedStateHandle(savedStateHandle).matchId

    private val _winTeamKdaInfo = MutableLiveData<TeamKdaInfo>()
    val winTeamKdaInfo: LiveData<TeamKdaInfo> = _winTeamKdaInfo

    private val _loseTeamKdaInfo = MutableLiveData<TeamKdaInfo>()
    val loseTeamKdaInfo: LiveData<TeamKdaInfo> = _loseTeamKdaInfo

    private val _winTeam = MutableLiveData<List<SummonerMatchRecord>>()
    val winTeam: LiveData<List<SummonerMatchRecord>> = _winTeam

    private val _loseTeam = MutableLiveData<List<SummonerMatchRecord>>()
    val loseTeam: LiveData<List<SummonerMatchRecord>> = _loseTeam

    init {
        if (matchId.isNotBlank()) {
            viewModelScope.launch {
                val data = getParticipantsMatches(matchId)
                val winTeam = data.filter { it.win }.map {
                    SummonerMatchRecord(it)
                }
                val loseTeam = data.filter { !it.win }.map {
                    SummonerMatchRecord(it)
                }

                _winTeam.value = winTeam
                _loseTeam.value = loseTeam

                _winTeamKdaInfo.value = TeamKdaInfo(
                    winTeam.sumOf { it.kill },
                    winTeam.sumOf { it.death },
                    winTeam.sumOf { it.assist },
                    true,
                    winTeam[0].playedTime
                )

                _loseTeamKdaInfo.value = TeamKdaInfo(
                    loseTeam.sumOf { it.kill },
                    loseTeam.sumOf { it.death },
                    loseTeam.sumOf { it.assist },
                    false,
                    loseTeam[0].playedTime
                )
            }
        }
    }
}