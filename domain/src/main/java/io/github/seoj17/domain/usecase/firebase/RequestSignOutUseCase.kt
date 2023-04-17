package io.github.seoj17.domain.usecase.firebase

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Reusable
import javax.inject.Inject

@Reusable
class RequestSignOutUseCase @Inject constructor() {
    operator fun invoke() {
        Firebase
            .auth
            .signOut()
    }
}
