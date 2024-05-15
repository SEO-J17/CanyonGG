package io.github.seoj17.domain.usecase.user

import dagger.Reusable
import io.github.seoj17.domain.model.UserRecordDomainModel
import io.github.seoj17.domain.usecase.match.GetRegisterUserMatchListUseCase
import javax.inject.Inject

@Reusable
class GetUserRecordUseCase @Inject constructor(
    private val getRegisterUserMatchListUseCase: GetRegisterUserMatchListUseCase,
) {
    suspend operator fun invoke(puuid: String): Result<UserRecordDomainModel> {
        return runCatching {
            getRegisterUserMatchListUseCase(puuid)
                .getOrThrow()
                .filterNotNull()
                .run {
                    val wholeMatch = size
                    val realMatch = wholeMatch - count { it.gameEndedInEarlySurrender }
                    val kills = sumOf { it.kills } + sumOf { it.assists }
                    
                    val win = count { it.win && !it.gameEndedInEarlySurrender }
                    val lose = realMatch - win
                    val winRate = (win * 100) / realMatch
                    val kda = kills / sumOf { it.deaths }.toDouble()
                    val mostKill = maxOf { it.largestKill }
                    
                    UserRecordDomainModel(wholeMatch, win, lose, winRate, kda, mostKill)
                }
        }
    }
}
