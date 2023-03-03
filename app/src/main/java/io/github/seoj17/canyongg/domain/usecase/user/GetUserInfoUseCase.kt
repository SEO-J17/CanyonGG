package io.github.seoj17.canyongg.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.SummonerRepository
import io.github.seoj17.canyongg.domain.model.SummonerDomainModel
import javax.inject.Inject

@Reusable
class GetUserInfoUseCase @Inject constructor(
    private val repository: SummonerRepository,
) {
    suspend operator fun invoke(userName: String): SummonerDomainModel? {
        return repository
            .getSummonerInfo(userName)
            ?.let {
                SummonerDomainModel(it)
            }
    }
}