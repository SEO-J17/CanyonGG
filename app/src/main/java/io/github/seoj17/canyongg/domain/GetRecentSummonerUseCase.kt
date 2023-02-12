package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.repository.SummonerRepository
import io.github.seoj17.canyongg.domain.model.DomainRecentSummoner
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@Reusable
class GetRecentSummonerUseCase @Inject constructor(
    private val repository: SummonerRepository
) {
    operator fun invoke(): Flow<List<DomainRecentSummoner>> {
        return repository
            .getRecentSummoner()
            .map {
                DomainRecentSummoner(it)
            }
    }
}