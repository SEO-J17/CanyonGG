package io.github.seoj17.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.data.repository.register.RepresentativeUserRepository
import io.github.seoj17.domain.model.RepresentativeUserInfoDomainModel
import javax.inject.Inject

@Reusable
class AddRepresentativeUserInfoUseCase @Inject constructor(
    private val repository: RepresentativeUserRepository,
) {
    suspend operator fun invoke(domain: RepresentativeUserInfoDomainModel) {
        repository.addMyUserInfo(
            RepresentativeUserInfoDomainModel.toEntity(domain),
        )
    }
}
