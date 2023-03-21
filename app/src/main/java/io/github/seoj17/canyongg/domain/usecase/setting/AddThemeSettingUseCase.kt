package io.github.seoj17.canyongg.domain.usecase.setting

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.DataStoreRepository
import javax.inject.Inject

@Reusable
class AddThemeSettingUseCase @Inject constructor(
    private val repository: DataStoreRepository,
) {
    suspend operator fun invoke(key: String, settingValue: Int) {
        repository.addThemeSetting(key, settingValue)
    }
}
