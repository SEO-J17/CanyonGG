package io.github.seoj17.canyongg.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.MyUserRepository
import io.github.seoj17.canyongg.domain.model.DomainMyUserInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetMyUserInfoUseCase @Inject constructor(
    private val repository: MyUserRepository,
) {
    operator fun invoke(): Flow<DomainMyUserInfo?> {
        return repository
            .getMyUserInfo()
            .map { entity ->
                entity?.let { it -> DomainMyUserInfo(it) }
            }
    }
}