package io.github.seoj17.presentation.ui.detail.analysisTab.pages.damaged

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.match.GetParticipantsMatchesUseCase
import io.github.seoj17.presentation.model.SummonerMatchRecord
import javax.inject.Inject

@HiltViewModel
class TeamDamagedViewModel @Inject constructor(
    private val getParticipantsMatches: GetParticipantsMatchesUseCase,
) : ViewModel() {
    private val _matchId = MutableLiveData<String>()
    val matchId: LiveData<String> = _matchId

    val participantsMatches = matchId.switchMap { matchId ->
        liveData {
            emit(
                getParticipantsMatches(matchId).map {
                    SummonerMatchRecord(it)
                },
            )
        }
    }

    val winTeamScore: LiveData<Int> = participantsMatches.map { matchList ->
        matchList
            .filter { it.win }
            .sumOf { it.totalDamaged }
    }

    val loseTeamScore: LiveData<Int> = participantsMatches.map { matchList ->
        matchList
            .filter { !it.win }
            .sumOf { it.totalDamaged }
    }

    fun setMatchId(matchId: String) {
        _matchId.value = matchId
    }
}
