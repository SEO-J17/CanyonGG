package io.github.seoj17.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.data.repository.summoner.SummonerRepository
import io.github.seoj17.domain.model.SummonerDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetUserInfoUseCase @Inject constructor(
    private val repository: SummonerRepository,
) {
    suspend operator fun invoke(userName: String?): Flow<SummonerDomainModel?> {
        return repository
            .getSummonerInfo(userName ?: "")
            .map { data ->
                data?.let {
                    SummonerDomainModel(it)
                }
            }
    }
}
