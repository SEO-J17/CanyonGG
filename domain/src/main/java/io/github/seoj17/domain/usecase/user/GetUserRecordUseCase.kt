package io.github.seoj17.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.domain.model.UserRecordDomainModel
import io.github.seoj17.domain.usecase.match.GetRepresentativeUserMatchListUseCase
import javax.inject.Inject

@Reusable
class GetUserRecordUseCase @Inject constructor(
    private val getRegisterUserMatchListUseCase: GetRepresentativeUserMatchListUseCase,
) {
    suspend operator fun invoke(puuid: String): UserRecordDomainModel {
        getRegisterUserMatchListUseCase(puuid)
            .filterNotNull()
            .run {
                val wholeMatch = size
                val realMatch = wholeMatch - count { it.gameEndedInEarlySurrender }
                val kills = sumOf { it.kills } + sumOf { it.assists }

                val win = count { it.win && !it.gameEndedInEarlySurrender }
                val lose = realMatch - win
                val winRate = (win * 100) / if (realMatch == 0) 1 else realMatch
                val kda = kills / sumOf { it.deaths }.toDouble()
                val mostKill = maxOf { it.largestKill }

                return UserRecordDomainModel(wholeMatch, win, lose, winRate, kda, mostKill)
            }
    }
}
