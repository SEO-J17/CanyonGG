package io.github.seoj17.presentaion.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMatchViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val matchId = DetailMatchFragmentArgs.fromSavedStateHandle(savedStateHandle).matchId
    val puuId = DetailMatchFragmentArgs.fromSavedStateHandle(savedStateHandle).puuid
}
