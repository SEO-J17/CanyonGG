package io.github.seoj17.canyongg.ui.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.domain.usecase.bookmark.AddBookmarkSummonerUseCase
import io.github.seoj17.canyongg.domain.usecase.summoner.AddSummonerInfoUseCase
import io.github.seoj17.canyongg.domain.usecase.bookmark.CheckBookmarkedSummonerUseCase
import io.github.seoj17.canyongg.domain.usecase.bookmark.DeleteBookmarkSummonerUseCase
import io.github.seoj17.canyongg.domain.usecase.summoner.GetSummonerHistoryUseCase
import io.github.seoj17.canyongg.domain.usecase.summoner.GetSummonerInfoUseCase
import io.github.seoj17.canyongg.domain.usecase.user.GetUserInfoUseCase
import io.github.seoj17.canyongg.domain.usecase.user.GetUserTierUseCase
import io.github.seoj17.canyongg.domain.usecase.user.GetUserRecordUseCase
import io.github.seoj17.canyongg.ui.model.Summoner
import io.github.seoj17.canyongg.ui.model.SummonerBookmark
import io.github.seoj17.canyongg.ui.model.SummonerInfo
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import io.github.seoj17.canyongg.ui.model.UserRecord
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummonerRecordViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserTierUseCase: GetUserTierUseCase,
    private val getSummonerHistoryUseCase: GetSummonerHistoryUseCase,
    private val addBookmarkSummoner: AddBookmarkSummonerUseCase,
    private val deleteBookmarkSummoner: DeleteBookmarkSummonerUseCase,
    private val checkBookmarkedSummoner: CheckBookmarkedSummonerUseCase,
    private val getSummonerInfoUseCase: GetSummonerInfoUseCase,
    private val addSummonerInfoUseCase: AddSummonerInfoUseCase,
    private val getUserRecordUseCase: GetUserRecordUseCase,
) : ViewModel() {

    private val summonerName =
        SummonerRecordFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerName

    private val summonerPuuid =
        SummonerRecordFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerPuuid

    val summonerInfo = getSummonerInfoUseCase(summonerPuuid)
        .asLiveData()
        .map { domain ->
            domain?.let { it -> SummonerInfo(it) }
        }

    private val _summonerMostKill = MutableLiveData<Int>()
    val summonerMostKill: LiveData<Int> = _summonerMostKill

    val summonerRecordHistory = getSummonerHistoryUseCase(summonerPuuid)
        .asLiveData()
        .map { paging ->
            paging.map {
                SummonerMatchRecord(it)
            }
        }
        .cachedIn(viewModelScope)

    private val _bookmarkedSummoner = MutableLiveData<Boolean?>()
    val bookmarkedSummoner: LiveData<Boolean?> = _bookmarkedSummoner

    init {
        viewModelScope.launch {
            _bookmarkedSummoner.value = checkBookmarkedSummoner(summonerPuuid)
            getUserInfoUseCase(summonerName)?.let { summonerDomain ->

                val summoner = Summoner(summonerDomain)

                val tier = getUserTierUseCase(summoner.id)?.let { summonerTier ->
                    "${summonerTier.tier} ${summonerTier.rank}"
                } ?: "UNRANKED"

                val userRecord = UserRecord(getUserRecordUseCase(summoner.puuid))
                _summonerMostKill.value = userRecord.largestKill

                insertSummonerInfoLocal(userRecord, summoner, tier)
            }
        }
    }

    private suspend fun insertSummonerInfoLocal(
        record: UserRecord,
        summoner: Summoner,
        tier: String,
    ) {
        addSummonerInfoUseCase(
            SummonerInfo.toDomainModel(summoner, record, tier)
        )
    }

    fun addBookmark() {
        val summonerInfo = summonerInfo.value!!
        viewModelScope.launch {
            addBookmarkSummoner(
                SummonerBookmark.toDomainModel(summonerInfo)
            )
        }
    }

    fun deleteBookmark() {
        viewModelScope.launch {
            deleteBookmarkSummoner(summonerName)
        }
    }
}

