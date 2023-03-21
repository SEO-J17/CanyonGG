package io.github.seoj17.canyongg.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.seoj17.canyongg.data.repository.ChampionsRepository
import io.github.seoj17.canyongg.data.repository.ChampionsRepositoryImpl
import io.github.seoj17.canyongg.data.repository.DataCenterRepository
import io.github.seoj17.canyongg.data.repository.DataCenterRepositoryImpl
import io.github.seoj17.canyongg.data.repository.DataStoreRepository
import io.github.seoj17.canyongg.data.repository.DataStoreRepositoryImpl
import io.github.seoj17.canyongg.data.repository.FirebaseRepository
import io.github.seoj17.canyongg.data.repository.FirebaseRepositoryImpl
import io.github.seoj17.canyongg.data.repository.MatchRepository
import io.github.seoj17.canyongg.data.repository.MatchRepositoryImpl
import io.github.seoj17.canyongg.data.repository.PerkRepository
import io.github.seoj17.canyongg.data.repository.PerkRepositoryImpl
import io.github.seoj17.canyongg.data.repository.RegisterUserRepository
import io.github.seoj17.canyongg.data.repository.RegisterUserRepositoryImpl
import io.github.seoj17.canyongg.data.repository.SummonerBookmarkRepository
import io.github.seoj17.canyongg.data.repository.SummonerBookmarkRepositoryImpl
import io.github.seoj17.canyongg.data.repository.SummonerInfoRepository
import io.github.seoj17.canyongg.data.repository.SummonerInfoRepositoryImpl
import io.github.seoj17.canyongg.data.repository.SummonerRepository
import io.github.seoj17.canyongg.data.repository.SummonerRepositoryImpl

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindSummonerRepository(summonerRepositoryImpl: SummonerRepositoryImpl): SummonerRepository

    @Binds
    abstract fun bindMatchesRepository(matchesRepositoryImpl: MatchRepositoryImpl): MatchRepository

    @Binds
    abstract fun bindDataCenterRepository(dataCenterRepositoryImpl: DataCenterRepositoryImpl): DataCenterRepository

    @Binds
    abstract fun bindSummonerBookmarkRepository(summonerBookmarkRepositoryImpl: SummonerBookmarkRepositoryImpl): SummonerBookmarkRepository

    @Binds
    abstract fun bindChampionsRepository(championsRepositoryImpl: ChampionsRepositoryImpl): ChampionsRepository

    @Binds
    abstract fun bindPerksRepository(perksRepositoryImpl: PerkRepositoryImpl): PerkRepository

    @Binds
    abstract fun bindMyUserRepository(myUserRepositoryImpl: RegisterUserRepositoryImpl): RegisterUserRepository

    @Binds
    abstract fun bindSummonerInfoRepository(summonerInfoRepositoryImpl: SummonerInfoRepositoryImpl): SummonerInfoRepository

    @Binds
    abstract fun bindFirebaseRepository(firebaseRepositoryImpl: FirebaseRepositoryImpl): FirebaseRepository

    @Binds
    abstract fun bindDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl): DataStoreRepository
}
