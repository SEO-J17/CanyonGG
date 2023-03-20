package io.github.seoj17.canyongg.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.RegisterUserRepository
import io.github.seoj17.canyongg.domain.model.RepresentativeUserInfoDomainModel
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
