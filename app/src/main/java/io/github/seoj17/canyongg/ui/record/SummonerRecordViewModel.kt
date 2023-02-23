package io.github.seoj17.canyongg.ui.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.domain.AddBookmarkSummoner
import io.github.seoj17.canyongg.domain.AddSummonerInfoUseCase
import io.github.seoj17.canyongg.domain.CheckBookmarkedSummonerUseCase
import io.github.seoj17.canyongg.domain.DeleteBookmarkSummonerUseCase
import io.github.seoj17.canyongg.domain.GetSummonerHistoryUseCase
import io.github.seoj17.canyongg.domain.GetSummonerInfoUseCase
import io.github.seoj17.canyongg.domain.GetSummonerUseCase
import io.github.seoj17.canyongg.domain.GetUserInfoUseCase
import io.github.seoj17.canyongg.domain.GetUserTierUseCase
import io.github.seoj17.canyongg.domain.model.DomainBookmarkSummoner
import io.github.seoj17.canyongg.domain.model.DomainSummonerInfo
import io.github.seoj17.canyongg.ui.model.ParticipantsMatches
import io.github.seoj17.canyongg.ui.model.SummonerInfo
import io.github.seoj17.canyongg.utils.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummonerRecordViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserTierUseCase: GetUserTierUseCase,
    private val getSummonerUseCase: GetSummonerUseCase,
    private val getSummonerHistoryUseCase: GetSummonerHistoryUseCase,
    private val addBookmarkSummoner: AddBookmarkSummoner,
    private val deleteBookmarkSummoner: DeleteBookmarkSummonerUseCase,
    private val checkBookmarkedSummoner: CheckBookmarkedSummonerUseCase,
    private val getSummonerInfoUseCase: GetSummonerInfoUseCase,
    private val addSummonerInfoUseCase: AddSummonerInfoUseCase,
) : ViewModel() {

    private val summonerName =
        SummonerRecordFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerName

    private val summonerPuuid =
        SummonerRecordFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerPuuid

    val summonerInfo =
        getSummonerInfoUseCase(summonerPuuid)
            .asLiveData()
            .map { domain ->
                domain?.let { it -> SummonerInfo(it) }
            }

    private val _summonerMostKill = MutableLiveData<String>("단일 킬")
    val summonerMostKill: LiveData<String> = _summonerMostKill

    val summonerRecordHistory =
        getSummonerHistoryUseCase(summonerPuuid).asLiveData().cachedIn(viewModelScope)

    private val _bookmarkedSummoner = MutableLiveData<Boolean>()
    val bookmarkedSummoner: LiveData<Boolean> = _bookmarkedSummoner

    private val _addBookmarkEvent = MutableLiveData<Event<Boolean>>()
    val addBookmarkEvent: LiveData<Event<Boolean>> = _addBookmarkEvent

    private val _deleteBookmarkEvent = MutableLiveData<Event<Boolean>>()
    val deleteBookmarkEvent: LiveData<Event<Boolean>> = _deleteBookmarkEvent

    init {
        viewModelScope.launch {
            _bookmarkedSummoner.value = checkBookmarkedSummoner(summonerPuuid)
            getUserInfoUseCase(summonerName)?.let { summonerInfo ->
                val tier = getUserTierUseCase(summonerInfo.id)?.let { summonerTier ->
                    "${summonerTier.tier} ${summonerTier.rank}"
                } ?: "UNRANKED"

                val matches = ParticipantsMatches(getSummonerUseCase(summonerInfo.puuid), "")
                if (matches.isNotEmpty()) {
                    calcSummonerInfo(matches, summonerInfo, tier)
                }
            }
        }
    }

    private fun calcSummonerInfo(
        myMatches: List<ParticipantsMatches>,
        summonerInfo: Summoner,
        tier: String
    ) {
        val wholeMatch = myMatches.size
        val realMatch = wholeMatch - myMatches.count { it.gameEndedInEarlySurrender }
        val kills = myMatches.sumOf { it.kills } + myMatches.sumOf { it.assists }

        val win = myMatches.count { it.win && !it.gameEndedInEarlySurrender }
        val lose = realMatch - win
        val winRate = (win * 100) / realMatch
        val kda = kills / myMatches.sumOf { it.deaths }.toDouble()
        val mostKill = myMatches.maxOf { it.largestMultiKill }

        _summonerMostKill.value = mostKillToName(mostKill)
        viewModelScope.launch {
            addSummonerInfoUseCase(
                DomainSummonerInfo(
                    puuid = summonerPuuid,
                    profile = summonerInfo.profileIconId,
                    level = summonerInfo.summonerLevel,
                    name = summonerInfo.name,
                    tier = tier,
                    wholeMatch = wholeMatch,
                    win = win,
                    lose = lose,
                    winRate = winRate,
                    kda = kda,
                    largestKill = mostKill,
                )
            )
        }
    }

    private fun mostKillToName(mostKill: Int): String {
        return when (mostKill) {
            2 -> "더블 킬"
            3 -> "트리플 킬"
            4 -> "쿼드라 킬"
            5 -> "펜타 킬"
            else -> ""
        }
    }

    fun addBookmark() {
        val summonerInfo = summonerInfo.value!!
        viewModelScope.launch {
            addBookmarkSummoner(
                DomainBookmarkSummoner(
                    summonerPuuid = summonerInfo.puuid,
                    summonerName = summonerInfo.name,
                    summonerLevel = summonerInfo.level,
                    summonerIcon = summonerInfo.profile,
                )
            )
        }
        _addBookmarkEvent.value = Event(true)
    }

    fun deleteBookmark() {
        viewModelScope.launch {
            deleteBookmarkSummoner(summonerName)
        }
        _deleteBookmarkEvent.value = Event(true)
    }
}

