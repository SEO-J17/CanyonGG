package io.github.seoj17.domain.usecase.setting

import dagger.Reusable
import io.github.seoj17.data.repository.datastore.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@Reusable
class GetThemeSettingUseCase @Inject constructor(
    private val repository: DataStoreRepository,
) {
    operator fun invoke(): Flow<Int?> {
        return repository.getThemeSetting()
    }
}
