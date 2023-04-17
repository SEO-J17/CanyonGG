package io.github.seoj17.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.seoj17.data.repository.ChampionsRepository
import io.github.seoj17.data.repository.DataCenterRepository
import io.github.seoj17.data.repository.DataStoreRepository
import io.github.seoj17.data.repository.FirebaseRepository
import io.github.seoj17.data.repository.MatchRepository
import io.github.seoj17.data.repository.MostChampRepository
import io.github.seoj17.data.repository.PerkRepository
import io.github.seoj17.data.repository.RegisterUserRepository
import io.github.seoj17.data.repository.SummonerBookmarkRepository
import io.github.seoj17.data.repository.SummonerInfoRepository
import io.github.seoj17.data.repository.SummonerRepository
import io.github.seoj17.domain.repositoryImpl.ChampionsRepositoryImpl
import io.github.seoj17.domain.repositoryImpl.DataCenterRepositoryImpl
import io.github.seoj17.domain.repositoryImpl.DataStoreRepositoryImpl
import io.github.seoj17.domain.repositoryImpl.FirebaseRepositoryImpl
import io.github.seoj17.domain.repositoryImpl.MatchRepositoryImpl
import io.github.seoj17.domain.repositoryImpl.MostChampRepositoryImpl
import io.github.seoj17.domain.repositoryImpl.PerkRepositoryImpl
import io.github.seoj17.domain.repositoryImpl.RegisterUserRepositoryImpl
import io.github.seoj17.domain.repositoryImpl.SummonerBookmarkRepositoryImpl
import io.github.seoj17.domain.repositoryImpl.SummonerInfoRepositoryImpl
import io.github.seoj17.domain.repositoryImpl.SummonerRepositoryImpl

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

    @Binds
    abstract fun bindMostChampRepository(mostChampionsRepositoryImpl: MostChampRepositoryImpl): MostChampRepository
}
