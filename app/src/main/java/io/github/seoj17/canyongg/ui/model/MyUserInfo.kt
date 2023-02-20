package io.github.seoj17.canyongg.ui.model

import io.github.seoj17.canyongg.domain.model.DomainMyUserInfo

data class MyUserInfo(
    val puuid: String,
    val profile: Int,
    val level: Int,
    val name: String,
    val tier: String,
    val wholeMatch: Int,
    val win: Int,
    val lose: Int,
    val winRate: Int,
    val kda: Double,
) {
    companion object {
        operator fun invoke(domain: DomainMyUserInfo): MyUserInfo {
            return MyUserInfo(
                puuid = domain.puuid,
                profile = domain.profile,
                level = domain.level,
                name = domain.name,
                tier = domain.tier,
                wholeMatch = domain.wholeMatch,
                win = domain.win,
                lose = domain.lose,
                winRate = domain.winRate,
                kda = domain.kda,
            )
        }
    }
}