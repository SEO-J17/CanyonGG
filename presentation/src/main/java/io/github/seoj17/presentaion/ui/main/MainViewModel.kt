package io.github.seoj17.presentaion.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.setting.GetThemeSettingUseCase
import io.github.seoj17.presentaion.ui.setting.ThemeState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getThemeSettingUseCase: GetThemeSettingUseCase,
) : ViewModel() {
    val themeSetting: LiveData<Int?> =
        getThemeSettingUseCase(ThemeState.getKey()).asLiveData()
}
