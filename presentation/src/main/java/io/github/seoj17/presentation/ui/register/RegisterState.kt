package io.github.seoj17.presentation.ui.register

sealed interface RegisterState {
    object SUCCESS : RegisterState
    object FAIL : RegisterState
}
