package io.github.seoj17.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.data.repository.register.RepresentativeUserRepository
import io.github.seoj17.domain.model.RepresentativeUserInfoDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetRepresentativeUserInfoUseCase @Inject constructor(
    private val repository: RepresentativeUserRepository,
) {
    operator fun invoke(): Flow<RepresentativeUserInfoDomainModel?> {
        return repository
            .getMyUserInfo()
            .map { data ->
                data?.let { it -> RepresentativeUserInfoDomainModel(it) }
            }
    }
}
