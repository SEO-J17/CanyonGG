package io.github.seoj17.presentaion.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.bookmark.DeleteBookmarkSummonerUseCase
import io.github.seoj17.domain.usecase.bookmark.GetBookmarkSummonerUseCase
import io.github.seoj17.domain.usecase.champion.AddMyMostChampsUseCase
import io.github.seoj17.domain.usecase.champion.GetChampionNameUseCase
import io.github.seoj17.domain.usecase.champion.GetMostChampUseCase
import io.github.seoj17.domain.usecase.champion.GetMostChampionListUseCase
import io.github.seoj17.domain.usecase.champion.GetRotationChampUseCase
import io.github.seoj17.domain.usecase.summoner.AddSummonerInfoUseCase
import io.github.seoj17.domain.usecase.user.AddRegisterUserInfoUseCase
import io.github.seoj17.domain.usecase.user.DeleteRegisterUserInfoUseCase
import io.github.seoj17.domain.usecase.user.GetRegisterUserInfoUseCase
import io.github.seoj17.domain.usecase.user.GetUserInfoUseCase
import io.github.seoj17.domain.usecase.user.GetUserRecordUseCase
import io.github.seoj17.domain.usecase.user.GetUserTierUseCase
import io.github.seoj17.presentaion.model.ChampInfo
import io.github.seoj17.presentaion.model.MostChamps
import io.github.seoj17.presentaion.model.RegisterUserInfo
import io.github.seoj17.presentaion.model.Summoner
import io.github.seoj17.presentaion.model.SummonerBookmark
import io.github.seoj17.presentaion.model.SummonerInfo
import io.github.seoj17.presentaion.model.UserRecord
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val getUserTierUseCase: GetUserTierUseCase,
    private val getBookmarkSummoner: GetBookmarkSummonerUseCase,
    private val deleteBookmarkSummoner: DeleteBookmarkSummonerUseCase,
    private val getRegisterUserInfoUseCase: GetRegisterUserInfoUseCase,
    private val getMostChampUseCase: GetMostChampUseCase,
    private val addMyUserInfo: AddRegisterUserInfoUseCase,
    private val addMyMostChamps: AddMyMostChampsUseCase,
    private val deleteMyUserInfo: DeleteRegisterUserInfoUseCase,
    private val addSummonerInfoUseCase: AddSummonerInfoUseCase,
    private val getRotationChamp: GetRotationChampUseCase,
    private val getChampionName: GetChampionNameUseCase,
    private val getUserRecordUseCase: GetUserRecordUseCase,
    private val getMostChampionListUseCase: GetMostChampionListUseCase,
) : ViewModel() {

    private val summonerName =
        HomeFragmentArgs.fromSavedStateHandle(savedStateHandle).summonerName ?: ""

    val userInfo = getRegisterUserInfoUseCase()
        .asLiveData()
        .map { domain ->
            domain?.let { it -> RegisterUserInfo(it) }
        }

    private val mostChampsInfo = getMostChampUseCase()
        .asLiveData()
        .map {
            MostChamps(it)
        }

    val firstMostChamp: LiveData<ChampInfo?> = setMostChamps(index = 0)

    val secondMostChamp: LiveData<ChampInfo?> = setMostChamps(index = 1)

    val thirdMostChamp: LiveData<ChampInfo?> = setMostChamps(index = 2)

    val bookmarkSummoners = getBookmarkSummoner()
        .asLiveData()
        .map {
            SummonerBookmark(it)
        }

    private val _rotationChamp = MutableLiveData<List<String>?>()
    val rotationChamp: LiveData<List<String>?> = _rotationChamp

    init {
        if (summonerName.isNotBlank()) {
            fetchData()
        }
        viewModelScope.launch {
            delay(10)
            val champIds = getRotationChamp()
            _rotationChamp.value = getChampionName(champIds)
        }
    }

    private fun setMostChamps(index: Int): LiveData<ChampInfo?> {
        return mostChampsInfo.map {
            it.getOrNull(index)
                ?.let { champ ->
                    ChampInfo(champ)
                }
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            getUserInfoUseCase(summonerName)?.let { summonerDomain ->

                val summoner = Summoner(summonerDomain)

                val tier = getUserTierUseCase(summoner.id)?.let { userTier ->
                    "${userTier.tier} ${userTier.rank}"
                } ?: "UNRANKED"

                val userRecord = UserRecord(getUserRecordUseCase(summoner.puuid))
                val mostChampList = ChampInfo(getMostChampionListUseCase(summoner.puuid))

                insertUserInfoLocal(summoner, userRecord, tier)
                insertMostChampLocal(summoner, mostChampList)
            }
        }
    }

    private suspend fun insertUserInfoLocal(
        summoner: Summoner,
        record: UserRecord,
        tier: String,
    ) {
        addMyUserInfo(
            RegisterUserInfo.toDomainModel(summoner, record, tier),
        )
        addSummonerInfoUseCase(
            SummonerInfo.toDomainModel(summoner, record, tier),
        )
    }

    private suspend fun insertMostChampLocal(summoner: Summoner, champs: List<ChampInfo>) {
        addMyMostChamps(
            champs.map {
                MostChamps.toDomainModel(summoner, it)
            },
        )
    }

    fun removeBookmark(name: String) {
        viewModelScope.launch {
            deleteBookmarkSummoner(name)
        }
    }

    fun removeMyInfo() {
        viewModelScope.launch {
            deleteMyUserInfo()
        }
    }

    fun refreshMyInfo() {
        viewModelScope.launch {
            fetchData()
        }
    }
}
