package io.github.seoj17.canyongg.ui.detail.analysisTab.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.domain.GetParticipantsMatches
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamWardViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getParticipantsMatches: GetParticipantsMatches,
) : ViewModel() {
    private val matchId = TeamWardFragmentArgs.fromSavedStateHandle(savedStateHandle).matchId

    private val _matchInfo = MutableLiveData<List<SummonerMatchRecord>>()
    val matchInfo: LiveData<List<SummonerMatchRecord>> = _matchInfo

    private val _winTeamScore = MutableLiveData<String>()
    val winTeamScore: LiveData<String> = _winTeamScore

    private val _loseTeamScore = MutableLiveData<String>()
    val loseTeamScore: LiveData<String> = _loseTeamScore

    init {
        viewModelScope.launch {
            getParticipantsMatches(matchId).map {
                SummonerMatchRecord(it)
            }.also { matchInfo ->
                _matchInfo.value = matchInfo
                _winTeamScore.value = matchInfo
                    .filter { it.win }
                    .sumOf { it.wardPlaced }
                    .toString()
                _loseTeamScore.value = matchInfo
                    .filter { !it.win }
                    .sumOf { it.wardPlaced }
                    .toString()
            }
        }
    }
}