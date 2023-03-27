package io.github.seoj17.canyongg.ui.login

sealed class LoginState {
    object SUCCESS : LoginState()
    object FAIL : LoginState()
}
