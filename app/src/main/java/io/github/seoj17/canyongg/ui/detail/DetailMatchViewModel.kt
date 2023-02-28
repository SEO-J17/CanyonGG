package io.github.seoj17.canyongg.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMatchViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    val matchId = DetailMatchFragmentArgs.fromSavedStateHandle(savedStateHandle).matchId
    val puuId = DetailMatchFragmentArgs.fromSavedStateHandle(savedStateHandle).puuid
}