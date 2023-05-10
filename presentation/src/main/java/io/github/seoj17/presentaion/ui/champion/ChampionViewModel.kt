package io.github.seoj17.presentaion.ui.champion

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.champion.GetAllChampionUseCase
import io.github.seoj17.presentaion.model.Champion
import javax.inject.Inject

@HiltViewModel
class ChampionViewModel @Inject constructor(
    private val getAllChampionUseCase: GetAllChampionUseCase,
) : ViewModel() {

    val champion: LiveData<List<Champion>> = liveData {
        emit(
            getAllChampionUseCase().map { Champion(it) },
        )
    }
}
