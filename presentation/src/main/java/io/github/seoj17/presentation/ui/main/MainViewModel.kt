package io.github.seoj17.presentation.ui.main

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.domain.usecase.setting.GetThemeSettingUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    getThemeSettingUseCase: GetThemeSettingUseCase,
) : ViewModel() {
    val themeSetting = getThemeSettingUseCase()
}
