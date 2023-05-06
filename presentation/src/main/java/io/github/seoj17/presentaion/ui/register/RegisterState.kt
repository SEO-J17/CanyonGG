package io.github.seoj17.presentaion.ui.register

sealed interface RegisterState {
    object SUCCESS : RegisterState
    object FAIL : RegisterState
}
