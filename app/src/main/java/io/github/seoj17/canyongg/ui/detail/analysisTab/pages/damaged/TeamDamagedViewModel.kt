package io.github.seoj17.canyongg.ui.detail.analysisTab.pages.damaged

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.domain.usecase.match.GetParticipantsMatchesUseCase
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDamagedViewModel @Inject constructor(
    private val getParticipantsMatches: GetParticipantsMatchesUseCase,
) : ViewModel() {
    private val _matchInfo = MutableLiveData<List<SummonerMatchRecord>>()
    val matchInfo: LiveData<List<SummonerMatchRecord>> = _matchInfo

    private val _winTeamScore = MutableLiveData<String>()
    val winTeamScore: LiveData<String> = _winTeamScore

    private val _loseTeamScore = MutableLiveData<String>()
    val loseTeamScore: LiveData<String> = _loseTeamScore

    private val _matchId = MutableLiveData<String>()
    val matchId: LiveData<String> = _matchId

    fun fetch() {
        _matchId.value?.let { matchId ->
            viewModelScope.launch {
                getParticipantsMatches(matchId).map {
                    SummonerMatchRecord(it)
                }.also { matchInfo ->
                    _matchInfo.value = matchInfo

                    _winTeamScore.value = matchInfo
                        .filter { it.win }
                        .sumOf { it.totalDamaged }
                        .toString()

                    _loseTeamScore.value = matchInfo
                        .filter { !it.win }
                        .sumOf { it.totalDamaged }
                        .toString()
                }
            }
        }
    }

    fun setMatchId(matchId: String) {
        _matchId.value = matchId
    }
}