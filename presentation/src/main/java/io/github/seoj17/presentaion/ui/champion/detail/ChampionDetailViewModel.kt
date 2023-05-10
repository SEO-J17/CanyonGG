package io.github.seoj17.presentaion.ui.champion.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.champion.GetChampionDetailUseCase
import io.github.seoj17.presentaion.model.Champion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getChampionDetailUseCase: GetChampionDetailUseCase,
) : ViewModel() {

    private val championKey =
        ChampionDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).champKey

    private val _champDetail = MutableLiveData<Champion>()
    val champDetail: LiveData<Champion> = _champDetail

    init {
        viewModelScope.launch {
            getChampionDetailUseCase(championKey)?.let {
                _champDetail.value = Champion(it)
            }
        }
    }
}
