package io.github.seoj17.presentaion.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.firebase.GetCurrentLoginUserUseCase
import io.github.seoj17.domain.usecase.firebase.RequestSignOutUseCase
import io.github.seoj17.domain.usecase.setting.AddThemeSettingUseCase
import io.github.seoj17.domain.usecase.setting.GetThemeSettingUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val addThemeSettingUseCase: AddThemeSettingUseCase,
    private val requestSignOutUseCase: RequestSignOutUseCase,
    getThemeSettingUseCase: GetThemeSettingUseCase,
    getCurrentLoginUserUseCase: GetCurrentLoginUserUseCase,
) : ViewModel() {

    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> = _loginState

    private val _currentEmail = MutableLiveData<String>()
    val currentEmail: LiveData<String> = _currentEmail

    val themeSetting = getThemeSettingUseCase().asLiveData()

    init {
        getCurrentLoginUserUseCase()
            ?.let {
                _loginState.value = true
                _currentEmail.value = it.email
            }
            ?: run {
                _loginState.value = false
            }
    }

    fun userLogout() {
        requestSignOutUseCase()
        _loginState.value = false
    }

    fun fetchThemeSetting(index: Int) {
        viewModelScope.launch {
            addThemeSettingUseCase(index)
        }
    }
}
