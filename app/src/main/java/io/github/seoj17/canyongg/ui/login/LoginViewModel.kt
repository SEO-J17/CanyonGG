package io.github.seoj17.canyongg.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    val emailValid = email.map {
        if (it.isBlank()) return@map false

        return@map Patterns.EMAIL_ADDRESS.matcher(it.toString()).matches()
    }

    val passwordValid: LiveData<Boolean> = password.map {
        return@map it.length > 5
    }

    val validLoginInfo =
        combine(emailValid.asFlow(), passwordValid.asFlow()) { emailValid, passwordValid ->
            (emailValid && passwordValid)
        }.asLiveData()

    private val _loginState = MutableSharedFlow<LoginState>()
    val loginState: SharedFlow<LoginState> = _loginState.asSharedFlow()

    fun loginSubmit() {
        Firebase
            .auth
            .signInWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener {
                viewModelScope.launch {
                    if (it.isSuccessful) {
                        _loginState.emit(LoginState.SUCCESS)
                    } else {
                        _loginState.emit(LoginState.FAIL)
                    }
                }
            }
    }
}
