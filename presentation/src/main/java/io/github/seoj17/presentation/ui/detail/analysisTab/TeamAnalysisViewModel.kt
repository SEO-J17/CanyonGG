package io.github.seoj17.presentation.ui.detail.analysisTab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TeamAnalysisViewModel @Inject constructor() : ViewModel() {

    private val _matchId = MutableLiveData<String>()
    val matchId: LiveData<String> = _matchId

    fun setMatchId(matchId: String) {
        _matchId.value = matchId
    }
}
