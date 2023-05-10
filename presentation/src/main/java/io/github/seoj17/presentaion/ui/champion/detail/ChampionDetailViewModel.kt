package io.github.seoj17.presentaion.ui.champion.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.champion.GetChampionDetailUseCase
import io.github.seoj17.presentaion.model.Champion
import javax.inject.Inject

@HiltViewModel
class ChampionDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getChampionDetailUseCase: GetChampionDetailUseCase,
) : ViewModel() {

    private val championKey =
        ChampionDetailFragmentArgs.fromSavedStateHandle(savedStateHandle).champKey

    val champDetail: LiveData<Champion> = liveData {
        getChampionDetailUseCase(championKey)?.let {
            emit(Champion(it))
        }
    }
}
