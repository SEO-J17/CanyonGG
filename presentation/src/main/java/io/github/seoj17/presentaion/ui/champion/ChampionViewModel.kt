package io.github.seoj17.presentaion.ui.champion

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.champion.GetAllChampionUseCase
import io.github.seoj17.presentaion.model.Champion
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionViewModel @Inject constructor(
    private val getAllChampionUseCase: GetAllChampionUseCase,
) : ViewModel() {

    private val _champion = MutableLiveData<List<Champion>>()
    val champion: LiveData<List<Champion>> = _champion

    init {
        viewModelScope.launch {
            _champion.value = getAllChampionUseCase().map { Champion(it) }
        }
    }
}
