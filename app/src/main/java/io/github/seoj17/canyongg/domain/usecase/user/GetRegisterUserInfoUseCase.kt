package io.github.seoj17.canyongg.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.RegisterUserRepository
import io.github.seoj17.canyongg.domain.model.RegisterUserInfoDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetRegisterUserInfoUseCase @Inject constructor(
    private val repository: RegisterUserRepository,
) {
    operator fun invoke(): Flow<RegisterUserInfoDomainModel?> {
        return repository
            .getMyUserInfo()
            .map { data ->
                data?.let { it -> RegisterUserInfoDomainModel(it) }
            }
    }
}