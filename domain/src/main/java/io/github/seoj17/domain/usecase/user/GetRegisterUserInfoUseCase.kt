package io.github.seoj17.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.data.repository.register.RegisterUserRepository
import io.github.seoj17.domain.model.RepresentativeUserInfoDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetRegisterUserInfoUseCase @Inject constructor(
    private val repository: RegisterUserRepository,
) {
    operator fun invoke(): Flow<RepresentativeUserInfoDomainModel?> {
        return repository
            .getMyUserInfo()
            .map { data ->
                data?.let { it -> RepresentativeUserInfoDomainModel(it) }
            }
    }
}
