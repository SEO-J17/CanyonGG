package io.github.seoj17.presentaion.ui.champion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.bookmark.AddBookmarkChampionUseCase
import io.github.seoj17.domain.usecase.bookmark.DeleteBookmarkChampionUseCase
import io.github.seoj17.domain.usecase.champion.GetAllChampionUseCase
import io.github.seoj17.presentaion.model.Champion
import io.github.seoj17.presentaion.model.ChampionBookmark
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChampionViewModel @Inject constructor(
    getAllChampionUseCase: GetAllChampionUseCase,
    private val addBookmarkChampionUseCase: AddBookmarkChampionUseCase,
    private val deleteBookmarkChampionUseCase: DeleteBookmarkChampionUseCase,
) : ViewModel() {

    private val _bookmarkEvent = MutableSharedFlow<Boolean>()
    val bookmarkEvent: SharedFlow<Boolean> = _bookmarkEvent

    val champion = getAllChampionUseCase()
        .map { Champion(it) }
        .stateIn(
            viewModelScope,
            SharingStarted.Lazily,
            emptyList(),
        )

    fun onBookmarkClick(champion: Champion) {
        viewModelScope.launch {
            if (champion.isBookmark) {
                deleteBookmarkChampionUseCase(champion.key)
                _bookmarkEvent.emit(false)
            } else {
                addBookmarkChampionUseCase(ChampionBookmark.toDomain(champion))
                _bookmarkEvent.emit(true)
            }
        }
    }
}
