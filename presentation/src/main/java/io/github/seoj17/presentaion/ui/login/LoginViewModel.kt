package io.github.seoj17.presentaion.ui.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.firebase.GetLoginStateUseCase
import io.github.seoj17.presentaion.utils.combineWith
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val getLoginStateUseCase: GetLoginStateUseCase,
) : ViewModel() {

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    val emailValid = email.map {
        if (it.isBlank()) return@map false

        return@map Patterns.EMAIL_ADDRESS.matcher(it.toString()).matches()
    }

    val passwordValid: LiveData<Boolean> = password.map {
        return@map it.length > 5
    }

    val validLoginInfo = combineWith(emailValid, passwordValid) { emailValid, passwordValid ->
        (emailValid == true && passwordValid == true)
    }

    private val _loginState = MutableSharedFlow<LoginState>()
    val loginState: Flow<LoginState> = _loginState.asSharedFlow()

    fun loginSubmit() {
        viewModelScope.launch {
            val result = getLoginStateUseCase(email.value, password.value)
            if (result.isSuccess) {
                _loginState.emit(LoginState.SUCCESS)
            } else {
                _loginState.emit(LoginState.FAIL)
            }
        }
    }
}
