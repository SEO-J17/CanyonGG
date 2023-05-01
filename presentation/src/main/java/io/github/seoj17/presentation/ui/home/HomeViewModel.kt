package io.github.seoj17.presentation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import io.github.seoj17.domain.usecase.champion.GetRotationChampIdListUseCase
import io.github.seoj17.domain.usecase.summoner.AddSummonerInfoUseCase
import io.github.seoj17.domain.usecase.user.AddRepresentativeUserInfoUseCase
import io.github.seoj17.domain.usecase.user.DeleteRepresentativeUserInfoUseCase
import io.github.seoj17.domain.usecase.user.GetRepresentativeUserInfoUseCase
import io.github.seoj17.domain.usecase.user.GetUserInfoUseCase
import io.github.seoj17.domain.usecase.user.GetUserRecordUseCase
import io.github.seoj17.domain.usecase.user.GetUserTierUseCase
import io.github.seoj17.presentation.model.ChampInfo
import io.github.seoj17.presentation.model.MostChamps
import io.github.seoj17.presentation.model.RegisterUserInfo
import io.github.seoj17.presentation.model.RotationChamp
import io.github.seoj17.presentation.model.Summoner
import io.github.seoj17.presentation.model.SummonerBookmark
import io.github.seoj17.presentation.model.SummonerInfo
import io.github.seoj17.presentation.model.UserRecord
import io.github.seoj17.presentation.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getBookmarkSummoner: GetBookmarkSummonerUseCase,
    private val deleteBookmarkSummoner: DeleteBookmarkSummonerUseCase,
    getRegisterUserInfoUseCase: GetRepresentativeUserInfoUseCase,
    getMostChampUseCase: GetMostChampUseCase,
    private val deleteMyUserInfo: DeleteRepresentativeUserInfoUseCase,
    private val getRotationChamp: GetRotationChampIdListUseCase,
    private val getChampionName: GetChampionNameUseCase,
    private val getUserTierUseCase: GetUserTierUseCase,
    private val getUserRecordUseCase: GetUserRecordUseCase,
    private val getMostChampionListUseCase: GetMostChampionListUseCase,
    private val addMyUserInfo: AddRepresentativeUserInfoUseCase,
    private val addMyMostChamps: AddMyMostChampsUseCase,
    private val addSummonerInfoUseCase: AddSummonerInfoUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
) : ViewModel() {

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

    private val _rotationChamp = MutableLiveData<List<RotationChamp>>()
    val rotationChamp: LiveData<List<RotationChamp>> = _rotationChamp

    private val _searchState = MutableStateFlow<UiState>(UiState.Empty)
    val searchState: StateFlow<UiState> = _searchState.asStateFlow()

    init {
        viewModelScope.launch {
            val list = getRotationChamp()
            _rotationChamp.value = getChampionName(list).map { RotationChamp(it) }
        }
    }

    fun fetchInfo(summoner: Summoner) {
        viewModelScope.launch {
            _searchState.emit(UiState.Loading)

            val tier = getUserTierUseCase(summoner.id)?.let { userTier ->
                "${userTier.tier} ${userTier.rank}"
            } ?: "UNRANKED"

            val userRecord = UserRecord(getUserRecordUseCase(summoner.puuid))
            val mostChampList = ChampInfo(getMostChampionListUseCase(summoner.puuid))

            insertUserInfoLocal(summoner, userRecord, tier)
            insertMostChampLocal(summoner, mostChampList)

            userInfo.value?.let {
                _searchState.emit(UiState.Success)
            }
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
            getUserInfoUseCase(userInfo.value?.name).collect {
                it?.let { data ->
                    fetchInfo(Summoner(data))
                }
            }
        }
    }
}
