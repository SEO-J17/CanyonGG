package io.github.seoj17.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.data.repository.summoner.SummonerRepository
import io.github.seoj17.domain.model.SummonerDomainModel
import javax.inject.Inject

@Reusable
class GetUserInfoUseCase @Inject constructor(
    private val repository: SummonerRepository,
) {
    suspend operator fun invoke(userName: String?): SummonerDomainModel? {
        return repository
            .getSummonerInfo(userName ?: "")
            ?.let {
                SummonerDomainModel(it)
            }
    }
}
