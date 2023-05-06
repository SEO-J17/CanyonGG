package io.github.seoj17.presentaion.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.recent.search.DeleteAllRecentSummonerUseCase
import io.github.seoj17.domain.usecase.recent.search.DeleteRecentSummonerUseCase
import io.github.seoj17.domain.usecase.recent.search.GetRecentSummonerUseCase
import io.github.seoj17.domain.usecase.summoner.AddSummonerUseCase
import io.github.seoj17.domain.usecase.user.GetUserInfoUseCase
import io.github.seoj17.presentaion.model.RecentSummoners
import io.github.seoj17.presentaion.model.Summoner
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchSummonerViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    getRecentSummonerUseCase: GetRecentSummonerUseCase,
    private val addSummonerUseCase: AddSummonerUseCase,
    private val deleteRecentSummonerUseCase: DeleteRecentSummonerUseCase,
    private val deleteAllRecentSummonerUseCase: DeleteAllRecentSummonerUseCase,
) : ViewModel() {

    val summonerName =
        SearchSummonerFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerName ?: ""

    val summonerPuuid =
        SearchSummonerFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerPuuid ?: ""

    private val _searchResult = MutableLiveData<Summoner?>()
    val searchResult: LiveData<Summoner?> = _searchResult

    val recentSearch: LiveData<List<RecentSummoners>> =
        getRecentSummonerUseCase()
            .asLiveData()
            .map {
                RecentSummoners(it)
            }

    fun validSearch(name: String) {
        viewModelScope.launch {
            getUserInfoUseCase(name)?.let { summonerDomain ->
                val summoner = Summoner(summonerDomain)

                _searchResult.value = summoner
                addSummonerUseCase(summoner.puuid, summoner.name)
            }
        }
    }

    fun isClickDetailInfo(): Boolean {
        return summonerName.isNotBlank() && summonerPuuid.isNotBlank()
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
