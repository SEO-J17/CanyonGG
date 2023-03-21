package io.github.seoj17.canyongg.ui.detail.summaryTab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.domain.usecase.match.GetParticipantsMatchesUseCase
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import io.github.seoj17.canyongg.ui.model.TeamKdaInfo
import javax.inject.Inject

@HiltViewModel
class MatchSummaryViewModel @Inject constructor(
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

    val winTeam: LiveData<List<SummonerMatchRecord>> = participantsMatches.map { matches ->
        matches.filter { it.win }
    }

    val loseTeam: LiveData<List<SummonerMatchRecord>> = participantsMatches.map { matches ->
        matches.filter { !it.win }
    }

    val winTeamKdaInfo = winTeam.map { matchList ->
        TeamKdaInfo.toKdaInfo(matchList, true)
    }

    val loseTeamKdaInfo = loseTeam.map { matchList ->
        TeamKdaInfo.toKdaInfo(matchList, false)
    }

    fun setMatchId(matchId: String) {
        _matchId.value = matchId
    }
}
