package io.github.seoj17.canyongg.ui.detail.analysisTab.pages.gold

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.domain.usecase.match.GetParticipantsMatchesUseCase
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamGoldViewModel @Inject constructor(
    private val getParticipantsMatches: GetParticipantsMatchesUseCase,
) : ViewModel() {
    private val _matchId = MutableLiveData<String>()
    val matchId: LiveData<String> = _matchId

    private val _participantsMatches = MutableLiveData<List<SummonerMatchRecord>>(emptyList())
    val participantsMatches: LiveData<List<SummonerMatchRecord>> = _participantsMatches

    val winTeamScore: LiveData<String> = _participantsMatches.map { matchList ->
        matchList
            .filter { it.win }
            .sumOf { it.spentGold }
            .toString()
    }

    val loseTeamScore: LiveData<String> = _participantsMatches.map { matchList ->
        matchList
            .filter { !it.win }
            .sumOf { it.spentGold }
            .toString()

    }

    fun fetch() {
        _matchId.value?.let { matchId ->
            viewModelScope.launch {
                _participantsMatches.value = getParticipantsMatches(matchId).map {
                    SummonerMatchRecord(it)
                }
            }
        }
    }

    fun setMatchId(matchId: String) {
        _matchId.value = matchId
    }
}