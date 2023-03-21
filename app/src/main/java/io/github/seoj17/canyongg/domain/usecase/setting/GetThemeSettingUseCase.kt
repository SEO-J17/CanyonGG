package io.github.seoj17.canyongg.domain.usecase.setting

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class GetThemeSettingUseCase @Inject constructor(
    private val repository: DataStoreRepository,
) {
    operator fun invoke(key: String): Flow<Int?> {
        return repository.getThemeSetting(key)
    }
}
