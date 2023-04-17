package io.github.seoj17.domain.usecase.firebase

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Reusable
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

@Reusable
class GetLoginStateUseCase @Inject constructor() {
    suspend operator fun invoke(email: String?, password: String?): Result<AuthResult> {
        return runCatching {
            Firebase
                .auth
                .signInWithEmailAndPassword(email ?: "", password ?: "")
                .await()
        }
    }
}
