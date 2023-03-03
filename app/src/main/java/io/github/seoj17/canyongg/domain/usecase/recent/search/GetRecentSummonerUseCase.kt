package io.github.seoj17.canyongg.domain.usecase.recent.search

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.SummonerRepository
import io.github.seoj17.canyongg.domain.model.RecentSummonerDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetRecentSummonerUseCase @Inject constructor(
    private val repository: SummonerRepository,
) {
    operator fun invoke(): Flow<List<RecentSummonerDomainModel>> {
        return repository
            .getRecentSummoner()
            .map {
                RecentSummonerDomainModel(it)
            }
    }
}