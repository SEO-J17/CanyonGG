package io.github.seoj17.presentaion.ui.search

import androidx.lifecycle.LiveData
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
    private val getUserInfoUseCase: GetUserInfoUseCase,
    getRecentSummonerUseCase: GetRecentSummonerUseCase,
    private val addSummonerUseCase: AddSummonerUseCase,
    private val deleteRecentSummonerUseCase: DeleteRecentSummonerUseCase,
    private val deleteAllRecentSummonerUseCase: DeleteAllRecentSummonerUseCase,
) : ViewModel() {

    val recentSearch: LiveData<List<RecentSummoners>> =
        getRecentSummonerUseCase()
            .asLiveData()
            .map {
                RecentSummoners(it)
            }

    suspend fun searchSummoner(name: String): Summoner? {
        return getUserInfoUseCase(name)?.let { summonerDomain ->
            val summoner = Summoner(summonerDomain)
            addSummonerUseCase(summoner.puuid, summoner.name)
            return@let summoner
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
