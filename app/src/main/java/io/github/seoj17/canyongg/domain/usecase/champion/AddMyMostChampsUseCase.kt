package io.github.seoj17.canyongg.domain.usecase.champion

import dagger.Reusable
import io.github.seoj17.canyongg.data.local.user.MyMostChampEntity
import io.github.seoj17.canyongg.data.repository.MyUserRepository
import io.github.seoj17.canyongg.domain.model.DomainMostChamps
import javax.inject.Inject

@Reusable
class AddMyMostChampsUseCase @Inject constructor(
    private val repository: MyUserRepository,
) {
    suspend operator fun invoke(domain: List<DomainMostChamps>) {
        repository.addMostChamps(
            domain.map {
                MyMostChampEntity(
                    champName = it.champName,
                    userPuuid = it.userPuuid,
                    champKda = it.champKda,
                    champWinRate = it.champWinRate,
                )
            }
        )
    }
}