package io.github.seoj17.presentaion.ui.register

sealed class RegisterState {
    object SUCCESS : RegisterState()
    object FAIL : RegisterState()
}
