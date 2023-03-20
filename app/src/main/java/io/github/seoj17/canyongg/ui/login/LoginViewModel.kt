package io.github.seoj17.canyongg.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private var emailValid = false

    private var passwordValid = false

    private val _loginEvent = MutableLiveData<Boolean>()
    val loginEvent: LiveData<Boolean> = _loginEvent

    private val _validInfo = MutableLiveData<Boolean>()
    val validInfo: LiveData<Boolean> = _validInfo

    fun loginSubmit(email: String, password: String) {
        Firebase
            .auth
            .signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                _loginEvent.value = task.isSuccessful
            }
    }

    fun fetchEmailValid(state: Boolean) {
        emailValid = state
        validInfo()
    }

    fun fetchPasswordValid(state: Boolean) {
        passwordValid = state
        validInfo()
    }

    private fun validInfo() {
        _validInfo.value = (emailValid && passwordValid)
    }
}
