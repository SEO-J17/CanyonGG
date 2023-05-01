package io.github.seoj17.presentation.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.recent.search.DeleteAllRecentSummonerUseCase
import io.github.seoj17.domain.usecase.recent.search.DeleteRecentSummonerUseCase
import io.github.seoj17.domain.usecase.recent.search.GetRecentSummonerUseCase
import io.github.seoj17.domain.usecase.summoner.AddRecentSummonerUseCase
import io.github.seoj17.domain.usecase.user.GetUserInfoUseCase
import io.github.seoj17.presentation.model.RecentSummoners
import io.github.seoj17.presentation.model.Summoner
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchSummonerViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
    getRecentSummonerUseCase: GetRecentSummonerUseCase,
    private val addRecentSummonerUseCase: AddRecentSummonerUseCase,
    private val deleteRecentSummonerUseCase: DeleteRecentSummonerUseCase,
    private val deleteAllRecentSummonerUseCase: DeleteAllRecentSummonerUseCase,
) : ViewModel() {

    private val _summonerInfo = MutableLiveData<Summoner?>()
    val summonerInfo: LiveData<Summoner?> = _summonerInfo

    val recentSearch: LiveData<List<RecentSummoners>> =
        getRecentSummonerUseCase()
            .asLiveData()
            .map {
                RecentSummoners(it)
            }

    fun validSearch(name: String) {
        viewModelScope.launch {
            getUserInfoUseCase(name).collect { domain ->
                _summonerInfo.value = domain?.let {
                    addRecentSummonerUseCase(domain.puuid, domain.name)
                    Summoner(domain)
                }
            }
        }
    }

    fun deleteRecentSummoner(name: String) {
        viewModelScope.launch {
            deleteRecentSummonerUseCase(name)
        }
    }

    fun deleteAllSearchHistory() {
        viewModelScope.launch {
            deleteAllRecentSummonerUseCase()
        }
    }
}
