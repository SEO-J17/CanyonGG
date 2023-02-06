package io.github.seoj17.canyongg.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.domain.GetUserInfoUseCase
import io.github.seoj17.canyongg.utils.Event
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterSummonerViewModel @Inject constructor(
    private val getUserInfoUseCase: GetUserInfoUseCase
) : ViewModel() {

    private val _searchResult = MutableLiveData<Summoner?>()
    val searchResult: LiveData<Summoner?> = _searchResult

    private val _errorEvent = MutableLiveData<Event<Boolean>>()
    val errorEvent: LiveData<Event<Boolean>> = _errorEvent

    fun getSummonerName(name: String) {
        viewModelScope.launch {
            getUserInfoUseCase(name)?.let { summoner ->
                _searchResult.value = summoner
            } ?: run {
                _errorEvent.value = Event(true)
            }
        }
    }
}

