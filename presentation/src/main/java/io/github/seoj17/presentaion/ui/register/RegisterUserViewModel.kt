package io.github.seoj17.presentaion.ui.register

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
class RegisterUserViewModel @Inject constructor() : ViewModel() {

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
        combine(
            emailValid.asFlow(),
            passwordValid.asFlow(),
            passwordChkValid.asFlow(),
        ) { emailValid, passwordValid, passwordChkValid ->
            (emailValid && passwordValid && passwordChkValid)
        }
            .asLiveData()

    private val _registerState = MutableSharedFlow<RegisterState>()
    val registerState: SharedFlow<RegisterState> = _registerState.asSharedFlow()

    fun registerSubmit() {
        Firebase
            .auth
            .createUserWithEmailAndPassword(email.value!!, password.value!!)
            .addOnCompleteListener { task ->
                viewModelScope.launch {
                    if (task.isSuccessful) {
                        _registerState.emit(RegisterState.SUCCESS)
                    } else {
                        _registerState.emit(RegisterState.FAIL)
                    }
                }
            }
    }
}
