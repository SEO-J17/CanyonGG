package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.data.repository.InfoRepository
import javax.inject.Inject

@Reusable
class GetUserInfoUseCase @Inject constructor(
    private val repository: InfoRepository
) {
    suspend operator fun invoke(userName: String): Summoner? {
        return repository.getSummonerInfo(userName)
    }
}