package io.github.seoj17.presentaion.ui.champion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.bookmark.AddBookmarkChampionUseCase
import io.github.seoj17.domain.usecase.bookmark.DeleteBookmarkChampionUseCase
import io.github.seoj17.domain.usecase.champion.GetAllChampionUseCase
import io.github.seoj17.presentaion.model.Champion
import io.github.seoj17.presentaion.model.ChampionBookmark
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionViewModel @Inject constructor(
    getAllChampionUseCase: GetAllChampionUseCase,
    private val addBookmarkChampionUseCase: AddBookmarkChampionUseCase,
    private val deleteBookmarkChampionUseCase: DeleteBookmarkChampionUseCase,
) : ViewModel() {

    val champion = getAllChampionUseCase().map {
        Champion(it)
    }.asLiveData()

    fun addBookmark(champion: Champion) {
        viewModelScope.launch {
            addBookmarkChampionUseCase(ChampionBookmark.toDomain(champion))
        }
    }

    fun deleteBookmark(champion: Champion) {
        viewModelScope.launch {
            deleteBookmarkChampionUseCase(champion.key)
        }
    }
}
