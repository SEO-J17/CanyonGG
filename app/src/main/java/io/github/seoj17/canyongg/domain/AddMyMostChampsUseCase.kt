package io.github.seoj17.canyongg.domain

import dagger.Reusable
import io.github.seoj17.canyongg.data.local.MyMostChampEntity
import io.github.seoj17.canyongg.data.repository.MyUserRepository
import io.github.seoj17.canyongg.domain.model.DomainMostChamps
import javax.inject.Inject

@Reusable
class AddMyMostChampsUseCase @Inject constructor(
    private val repository: MyUserRepository
) {
    suspend operator fun invoke(domain: DomainMostChamps) {
        repository.addMostChamps(
            MyMostChampEntity(
                userPuuid = domain.userPuuid,
                firstMostChamp = domain.firstMostChamp,
                secondMostChamp = domain.secondMostChamp,
                thirdMostChamp = domain.thirdMostChamp,
                firstChampKda = domain.firstChampKda,
                secondChampKda = domain.secondChampKda,
                thirdChampKda = domain.thirdChampKda,
                firstChampWinRate = domain.firstChampWinRate,
                secondChampWinRate = domain.secondChampWinRate,
                thirdChampWinRate = domain.thirdChampWinRate,
            )
        )
    }
}