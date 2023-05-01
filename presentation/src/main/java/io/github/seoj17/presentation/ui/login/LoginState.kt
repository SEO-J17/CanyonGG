package io.github.seoj17.presentation.ui.login

sealed interface LoginState {
    object SUCCESS : LoginState
    object FAIL : LoginState
}
