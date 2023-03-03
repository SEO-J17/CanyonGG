package io.github.seoj17.canyongg.ui.model

import io.github.seoj17.canyongg.domain.model.UserRecordDomainModel

data class UserRecord(
    val wholeMatch: Int = 0,
    val winCount: Int = 0,
    val loseCount: Int = 0,
    val winRate: Int = 0,
    val kda: Double = 0.0,
    val largestKill: Int = 0,
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