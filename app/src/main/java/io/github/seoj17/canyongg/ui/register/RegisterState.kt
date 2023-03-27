package io.github.seoj17.canyongg.ui.register

sealed class RegisterState {
    object SUCCESS : RegisterState()
    object FAIL : RegisterState()
}
