package io.github.seoj17.canyongg.domain.usecase.firebase

import io.github.seoj17.canyongg.contract.RemoteConfigContract
import io.github.seoj17.canyongg.data.repository.FirebaseRepository
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
