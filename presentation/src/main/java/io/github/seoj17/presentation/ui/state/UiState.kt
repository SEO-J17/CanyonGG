package io.github.seoj17.presentation.ui.state

sealed interface UiState {
    object Success : UiState
    object Empty : UiState
    object Loading : UiState
}
