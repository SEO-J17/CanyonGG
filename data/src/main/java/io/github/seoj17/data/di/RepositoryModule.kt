package io.github.seoj17.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.seoj17.data.repository.champion.ChampionsRepository
import io.github.seoj17.data.repository.champion.ChampionsRepositoryImpl
import io.github.seoj17.data.repository.datacenter.DataCenterRepository
import io.github.seoj17.data.repository.datacenter.DataCenterRepositoryImpl
import io.github.seoj17.data.repository.datastore.DataStoreRepository
import io.github.seoj17.data.repository.datastore.DataStoreRepositoryImpl
import io.github.seoj17.data.repository.firebase.FirebaseRepository
import io.github.seoj17.data.repository.firebase.FirebaseRepositoryImpl
import io.github.seoj17.data.repository.match.MatchRepository
import io.github.seoj17.data.repository.match.MatchRepositoryImpl
import io.github.seoj17.data.repository.mostchamp.MostChampRepository
import io.github.seoj17.data.repository.mostchamp.MostChampRepositoryImpl
import io.github.seoj17.data.repository.perk.PerkRepository
import io.github.seoj17.data.repository.perk.PerkRepositoryImpl
import io.github.seoj17.data.repository.register.RegisterUserRepository
import io.github.seoj17.data.repository.register.RegisterUserRepositoryImpl
import io.github.seoj17.data.repository.summoner.SummonerBookmarkRepository
import io.github.seoj17.data.repository.summoner.SummonerBookmarkRepositoryImpl
import io.github.seoj17.data.repository.summoner.SummonerInfoRepository
import io.github.seoj17.data.repository.summoner.SummonerInfoRepositoryImpl
import io.github.seoj17.data.repository.summoner.SummonerRepository
import io.github.seoj17.data.repository.summoner.SummonerRepositoryImpl

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
