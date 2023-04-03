package io.github.seoj17.presentaion.ui.register

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.firebase.RegisterUserUseCase
import io.github.seoj17.presentaion.utils.combineWith
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterUserViewModel @Inject constructor(
    private val registerUserUseCase: RegisterUserUseCase,
) : ViewModel() {

    val email = MutableLiveData<String>()
    val password = MutableLiveData<String>()
    val passwordChk = MutableLiveData<String>()

    val emailValid = email.map {
        if (it.isBlank()) return@map false

        return@map Patterns
            .EMAIL_ADDRESS
            .matcher(it.toString())
            .matches()
    }

    val passwordValid: LiveData<Boolean> = password.map {
        return@map it.length > 5
    }

    val passwordChkValid: LiveData<Boolean> = passwordChk.map {
        return@map it == password.value
    }

    val validLoginInfo =
        combineWith(
            emailValid,
            passwordValid,
            passwordChkValid,
        ) { emailValid, passwordValid, passwordChkValid ->
            (emailValid == true && passwordValid == true && passwordChkValid == true)
        }

    private val _registerState = MutableSharedFlow<RegisterState>()
    val registerState: Flow<RegisterState> = _registerState.asSharedFlow()

    fun registerSubmit() {
        registerUserUseCase(email.value!!, password.value!!).run {
            viewModelScope.launch {
                if (isSuccessful) {
                    _registerState.emit(RegisterState.SUCCESS)
                } else {
                    _registerState.emit(RegisterState.FAIL)
                }
            }
        }
    }
}
