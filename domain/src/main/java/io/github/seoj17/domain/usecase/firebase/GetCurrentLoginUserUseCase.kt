package io.github.seoj17.domain.usecase.firebase

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetCurrentLoginUserUseCase @Inject constructor() {
    operator fun invoke(): FirebaseUser? {
        return Firebase
            .auth
            .currentUser
    }
}
