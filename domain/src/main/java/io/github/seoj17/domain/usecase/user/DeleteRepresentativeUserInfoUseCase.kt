package io.github.seoj17.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.data.repository.register.RepresentativeUserRepository
import javax.inject.Inject

@Reusable
class DeleteRepresentativeUserInfoUseCase @Inject constructor(
    private val repository: RepresentativeUserRepository,
) {
    suspend operator fun invoke() {
        return repository.deleteMyUserChamps()
    }
}
