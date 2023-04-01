package io.github.seoj17.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.data.repository.RegisterUserRepository
import io.github.seoj17.domain.model.RepresentativeUserInfoDomainModel
import javax.inject.Inject

@Reusable
class AddRegisterUserInfoUseCase @Inject constructor(
    private val repository: RegisterUserRepository,
) {
    suspend operator fun invoke(domain: RepresentativeUserInfoDomainModel) {
        repository.addMyUserInfo(
            RepresentativeUserInfoDomainModel.toEntity(domain),
        )
    }
}
