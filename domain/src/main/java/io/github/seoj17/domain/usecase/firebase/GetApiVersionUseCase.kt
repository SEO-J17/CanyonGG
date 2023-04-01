package io.github.seoj17.domain.usecase.firebase

import io.github.seoj17.data.contract.RemoteConfigContract
import io.github.seoj17.data.repository.FirebaseRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetApiVersionUseCase @Inject constructor(
    private val firebaseRepository: FirebaseRepository,
) {
    operator fun invoke(): String {
        return firebaseRepository
            .getRemoteConfig()
            .getString(RemoteConfigContract.REMOTE_CONFIG_KEY)
    }
}
