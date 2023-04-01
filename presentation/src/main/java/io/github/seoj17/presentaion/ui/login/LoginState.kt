package io.github.seoj17.presentaion.ui.login

sealed class LoginState {
    object SUCCESS : LoginState()
    object FAIL : LoginState()
}
