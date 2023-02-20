package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.MyUserRepository
import javax.inject.Inject

@Reusable
class DeleteMyUserInfoUseCase @Inject constructor(
    private val repository: MyUserRepository
) {
    suspend operator fun invoke() {
        return repository.deleteMyUserChamps()
    }
}
