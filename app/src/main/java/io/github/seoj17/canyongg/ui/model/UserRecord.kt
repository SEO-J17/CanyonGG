package io.github.seoj17.canyongg.ui.model

import io.github.seoj17.canyongg.domain.model.UserRecordDomainModel

data class UserRecord(
    val wholeMatch: Int,
    val winCount: Int,
    val loseCount: Int,
    val winRate: Int,
    val kda: Double,
    val largestKill: Int,
) {
    companion object {
        operator fun invoke(domain: UserRecordDomainModel): UserRecord {
            return UserRecord(
                wholeMatch = domain.wholeMatch,
                winCount = domain.winCount,
                loseCount = domain.loseCount,
                winRate = domain.winRate,
                kda = domain.kda,
                largestKill = domain.largestKill,
            )
        }
    }
}