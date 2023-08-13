package io.github.seoj17.presentaion.ui.representative

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.user.GetUserInfoUseCase
import io.github.seoj17.presentaion.model.Summoner
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RepresentativeSummonerViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase,
) : ViewModel() {

    val summonerName = MutableLiveData<String>()

    private val _searchResult = MutableLiveData<Summoner?>()
    val searchResult: LiveData<Summoner?> = _searchResult

    fun searchSummoner() {
        viewModelScope.launch {
            _searchResult.value = getUserInfoUseCase(summonerName.value)?.let { summonerDomain ->
                Summoner(summonerDomain)
            }
        }
    }
}
