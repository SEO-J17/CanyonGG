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

    private val _summonerInfo = MutableLiveData<Summoner?>()
    val summonerInfo: LiveData<Summoner?> = _summonerInfo

    fun searchSummoner() {
        viewModelScope.launch {
            getUserInfoUseCase(summonerName.value).collect {
                _summonerInfo.value = it?.let { data ->
                    Summoner(data)
                }
            }
        }
    }
}
