package io.github.seoj17.canyongg.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.seoj17.canyongg.domain.usecase.setting.GetThemeSettingUseCase
import io.github.seoj17.canyongg.ui.setting.ThemeState
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getThemeSettingUseCase: GetThemeSettingUseCase,
) : ViewModel() {
    val themeSetting: LiveData<Int?> =
        getThemeSettingUseCase(ThemeState.getKey()).asLiveData()
}
