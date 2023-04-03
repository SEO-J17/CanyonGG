package io.github.seoj17.domain.usecase.setting

import dagger.Reusable
import io.github.seoj17.data.repository.DataStoreRepository
import javax.inject.Inject

@Reusable
class AddThemeSettingUseCase @Inject constructor(
    private val repository: DataStoreRepository,
) {
    suspend operator fun invoke(settingValue: Int) {
        repository.addThemeSetting(settingValue)
    }
}
