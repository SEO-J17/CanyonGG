package io.github.seoj17.presentaion.ui.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.bookmark.AddBookmarkSummonerUseCase
import io.github.seoj17.domain.usecase.bookmark.CheckBookmarkedSummonerUseCase
import io.github.seoj17.domain.usecase.bookmark.DeleteBookmarkSummonerUseCase
import io.github.seoj17.domain.usecase.summoner.AddSummonerInfoUseCase
import io.github.seoj17.domain.usecase.summoner.GetSummonerHistoryUseCase
import io.github.seoj17.domain.usecase.summoner.GetSummonerInfoUseCase
import io.github.seoj17.domain.usecase.user.GetUserRecordUseCase
import io.github.seoj17.domain.usecase.user.GetUserTierUseCase
import io.github.seoj17.presentaion.model.Summoner
import io.github.seoj17.presentaion.model.SummonerBookmark
import io.github.seoj17.presentaion.model.SummonerInfo
import io.github.seoj17.presentaion.model.SummonerMatchRecord
import io.github.seoj17.presentaion.model.UserRecord
import io.github.seoj17.presentaion.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummonerRecordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getUserTierUseCase: GetUserTierUseCase,
    getSummonerHistoryUseCase: GetSummonerHistoryUseCase,
    private val addBookmarkSummoner: AddBookmarkSummonerUseCase,
    private val deleteBookmarkSummoner: DeleteBookmarkSummonerUseCase,
    checkBookmarkedSummoner: CheckBookmarkedSummonerUseCase,
    getSummonerInfoUseCase: GetSummonerInfoUseCase,
    private val addSummonerInfoUseCase: AddSummonerInfoUseCase,
    private val getUserRecordUseCase: GetUserRecordUseCase,
) : ViewModel() {

    private val summonerName =
        SummonerRecordFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerName

    private val summonerPuuid =
        SummonerRecordFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerPuuid

    private val _searchState = MutableStateFlow<UiState>(UiState.Empty)
    val searchState: StateFlow<UiState> = _searchState.asStateFlow()

    val summonerInfo = liveData {
        getSummonerInfoUseCase(summonerPuuid).collect { domain ->
            domain?.let {
                _summonerMostKill.value = it.largestKill
                emit(SummonerInfo(it))
            }
        }
    }

    private val _summonerMostKill = MutableLiveData<Int>()
    val summonerMostKill: LiveData<Int> = _summonerMostKill

    val summonerRecordHistory = liveData {
        _searchState.emit(UiState.Loading)
        getSummonerHistoryUseCase(summonerPuuid).collect { paging ->
            emit(
                paging.map {
                    _searchState.emit(UiState.Success)
                    SummonerMatchRecord(it)
                },
            )
        }
    }.cachedIn(viewModelScope)

    val bookmarkedSummoner = checkBookmarkedSummoner(summonerPuuid).asLiveData()

    fun fetchSummonerInfo(summoner: Summoner) {
        viewModelScope.launch {
            val tier = getUserTierUseCase(summoner.name)?.let { summonerTier ->
                "${summonerTier.tier} ${summonerTier.rank}"
            } ?: "UNRANKED"

            val userRecord = UserRecord(getUserRecordUseCase(summoner.puuid))
            _summonerMostKill.value = userRecord.largestKill

            insertSummonerInfoLocal(userRecord, summoner, tier)
        }
    }

    private suspend fun insertSummonerInfoLocal(
        record: UserRecord,
        summoner: Summoner,
        tier: String,
    ) {
        addSummonerInfoUseCase(
            SummonerInfo.toDomainModel(summoner, record, tier),
        )
    }

    fun addBookmark() {
        val summonerInfo = summonerInfo.value!!
        viewModelScope.launch {
            addBookmarkSummoner(
                SummonerBookmark.toDomainModel(summonerInfo),
            )
        }
    }

    fun deleteBookmark() {
        viewModelScope.launch {
            deleteBookmarkSummoner(summonerName)
        }
    }
}
