package io.github.seoj17.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.data.repository.RegisterUserRepository
import javax.inject.Inject

@Reusable
class DeleteRegisterUserInfoUseCase @Inject constructor(
    private val repository: RegisterUserRepository,
) {
    suspend operator fun invoke() {
        return repository.deleteMyUserChamps()
    }
}
