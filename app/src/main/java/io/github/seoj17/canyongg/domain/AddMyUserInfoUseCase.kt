package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.local.MyUserInfoEntity
import io.github.seoj17.canyongg.data.repository.MyUserRepository
import io.github.seoj17.canyongg.domain.model.DomainMyUserInfo
import javax.inject.Inject

@Reusable
class AddMyUserInfoUseCase @Inject constructor(
    private val repository: MyUserRepository
) {
    suspend operator fun invoke(domain: DomainMyUserInfo) {
        repository.addMyUserInfo(
            MyUserInfoEntity(
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
        )
    }
}