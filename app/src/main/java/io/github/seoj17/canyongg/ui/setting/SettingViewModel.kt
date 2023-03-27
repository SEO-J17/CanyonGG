package io.github.seoj17.canyongg.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.domain.usecase.setting.AddThemeSettingUseCase
import io.github.seoj17.canyongg.domain.usecase.setting.GetThemeSettingUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val addThemeSettingUseCase: AddThemeSettingUseCase,
    private val getThemeSettingUseCase: GetThemeSettingUseCase,
) : ViewModel() {

    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> = _loginState

    private val _currentEmail = MutableLiveData<String>()
    val currentEmail: LiveData<String> = _currentEmail

    val themeSetting: LiveData<Int?> = getThemeSettingUseCase(ThemeState.getKey()).asLiveData()

    init {
        Firebase
            .auth
            .currentUser
            ?.let {
                _loginState.value = true
                _currentEmail.value = it.email
            }
            ?: run {
                _loginState.value = false
            }
    }

    fun userLogout() {
        Firebase.auth.signOut()
        _loginState.value = false
    }

    fun fetchThemeSetting(index: Int) {
        viewModelScope.launch {
            addThemeSettingUseCase(ThemeState.getKey(), ThemeState.getMode(index))
        }
    }
}
