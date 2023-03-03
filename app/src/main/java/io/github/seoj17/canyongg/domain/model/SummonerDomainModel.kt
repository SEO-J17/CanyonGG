package io.github.seoj17.canyongg.domain.model

import io.github.seoj17.canyongg.data.model.SummonerDataModel

data class SummonerDomainModel(
    val id: String,
    val accountId: String,
    val puuid: String,
    val name: String,
    val profileIconId: Int,
    val revisionDate: Long,
    val summonerLevel: Int,
) {
    companion object {
        operator fun invoke(data: SummonerDataModel): SummonerDomainModel {
            return SummonerDomainModel(
                id = data.id,
                accountId = data.accountId,
                puuid = data.puuid,
                name = data.name,
                profileIconId = data.profileIconId,
                revisionDate = data.revisionDate,
                summonerLevel = data.summonerLevel,
            )
        }
    }
}