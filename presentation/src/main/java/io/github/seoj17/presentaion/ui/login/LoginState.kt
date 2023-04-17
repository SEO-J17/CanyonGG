package io.github.seoj17.presentaion.ui.login

sealed interface LoginState {
    object SUCCESS : LoginState
    object FAIL : LoginState
}
