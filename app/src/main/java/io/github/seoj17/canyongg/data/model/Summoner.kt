package io.github.seoj17.canyongg.data.model

import android.os.Parcelable
import io.github.seoj17.canyongg.data.remote.response.summoner.SummonerResponse
import kotlinx.parcelize.Parcelize

@Parcelize
data class Summoner(
    val id: String,
    val accountId: String,
    val puuid: String,
    val name: String,
    val profileIconId: Int,
    val revisionDate: Long,
    val summonerLevel: Int,
) : Parcelable {
    companion object {
        operator fun invoke(response: SummonerResponse): Summoner {
            return Summoner(
                id = response.id,
                accountId = response.accountId,
                puuid = response.puuid,
                name = response.name,
                profileIconId = response.profileIconId,
                revisionDate = response.revisionDate,
                summonerLevel = response.summonerLevel,
            )
        }
    }
}
