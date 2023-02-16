package io.github.seoj17.canyongg.ui.record

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.domain.GetSummonerHistoryUseCase
import io.github.seoj17.canyongg.domain.GetSummonerUseCase
import io.github.seoj17.canyongg.domain.GetUserInfoUseCase
import io.github.seoj17.canyongg.domain.GetUserTierUseCase
import io.github.seoj17.canyongg.ui.model.ParticipantsMatches
import io.github.seoj17.canyongg.ui.model.SummonerRecord
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SummonerRecordViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserTierUseCase: GetUserTierUseCase,
    private val getSummonerUseCase: GetSummonerUseCase,
    private val getSummonerHistoryUseCase: GetSummonerHistoryUseCase,
) : ViewModel() {

    private val summonerName =
        SummonerRecordFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerName

    private val summonerPuuid =
        SummonerRecordFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerPuuid

    private val _summonerInfo = MutableLiveData<Summoner>()
    val summonerInfo: LiveData<Summoner> = _summonerInfo

    private val _summonerTier = MutableLiveData<String>("UNRANKED")
    val summonerTier: LiveData<String> = _summonerTier

    private val _summonerRecord = MutableLiveData<SummonerRecord>()
    val summonerRecord: LiveData<SummonerRecord> = _summonerRecord

    val summonerRecordHistory =
        getSummonerHistoryUseCase(summonerPuuid)
            .asLiveData()
            .cachedIn(viewModelScope)

    init {
        viewModelScope.launch {
            getUserInfoUseCase(summonerName)?.let { summonerInfo ->
                _summonerInfo.value = summonerInfo
                getUserTierUseCase(summonerInfo.id)?.let { summonerTier ->
                    _summonerTier.value = "${summonerTier.tier} ${summonerTier.rank}"
                }

                val matches = ParticipantsMatches(getSummonerUseCase(summonerInfo.puuid), "")
                if (matches.isNotEmpty()) {
                    calcSummonerInfo(matches)
                }
            }
        }
    }

    private fun calcSummonerInfo(myMatches: List<ParticipantsMatches>) {
        val wholeMatch = myMatches.size
        val realMatch = wholeMatch - myMatches.count { it.gameEndedInEarlySurrender }
        val kills = myMatches.sumOf { it.kills } + myMatches.sumOf { it.assists }

        val win = myMatches.count { it.win && !it.gameEndedInEarlySurrender }
        val lose = realMatch - win
        val winRate = (win * 100) / realMatch
        val kda = kills / myMatches.sumOf { it.deaths }.toDouble()
        val mostKill = mostKillToName(myMatches.maxOf { it.largestMultiKill })

        _summonerRecord.value = SummonerRecord(win, lose, winRate, kda, mostKill)
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
}

