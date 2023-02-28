package io.github.seoj17.canyongg.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.data.repository.SummonerRepository
import javax.inject.Inject

@Reusable
class GetUserInfoUseCase @Inject constructor(
    private val repository: SummonerRepository,
) {
    suspend operator fun invoke(userName: String): Summoner? {
        return repository.getSummonerInfo(userName)
    }
}