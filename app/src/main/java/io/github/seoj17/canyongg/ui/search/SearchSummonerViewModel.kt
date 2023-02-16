package io.github.seoj17.canyongg.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.domain.AddSummonerUseCase
import io.github.seoj17.canyongg.domain.DeleteAllRecentSummonerUseCase
import io.github.seoj17.canyongg.domain.DeleteRecentSummonerUseCase
import io.github.seoj17.canyongg.domain.GetRecentSummonerUseCase
import io.github.seoj17.canyongg.domain.GetUserInfoUseCase
import io.github.seoj17.canyongg.domain.model.DomainRecentSummoner
import io.github.seoj17.canyongg.utils.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchSummonerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getRecentSummonerUseCase: GetRecentSummonerUseCase,
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

    val recentSearch: LiveData<List<DomainRecentSummoner>> = getRecentSummonerUseCase().asLiveData()

    private val _errorEvent = MutableLiveData<Event<Boolean>>()
    val errorEvent: LiveData<Event<Boolean>> = _errorEvent

    val searchSummoner = { name: String ->
        viewModelScope.launch {
            getUserInfoUseCase(name)?.let { summoner ->
                _searchResult.value = summoner
                addSummonerUseCase(summoner.puuid, summoner.name)
            } ?: run {
                _errorEvent.value = Event(true)
            }
        }
    }

    fun isSetArguments(): Boolean {
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