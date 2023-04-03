package io.github.seoj17.domain.usecase.firebase

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Reusable
import javax.inject.Inject

@Reusable
class GetLoginStateUseCase @Inject constructor() {
    operator fun invoke(email: String, password: String): Task<AuthResult> {
        return Firebase
            .auth
            .signInWithEmailAndPassword(email, password)
    }
}
