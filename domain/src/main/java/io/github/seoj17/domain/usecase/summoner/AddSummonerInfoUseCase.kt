package io.github.seoj17.domain.usecase.summoner

import dagger.Reusable
import io.github.seoj17.data.repository.SummonerInfoRepository
import io.github.seoj17.domain.model.SummonerInfoDomainModel
import javax.inject.Inject

@Reusable
class AddSummonerInfoUseCase @Inject constructor(
    private val summonerInfoRepository: SummonerInfoRepository,
) {
    suspend operator fun invoke(domain: SummonerInfoDomainModel) {
        summonerInfoRepository.addSummonerInfo(
            SummonerInfoDomainModel.toEntity(domain),
        )
    }
}
