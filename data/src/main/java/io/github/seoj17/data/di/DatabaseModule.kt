package io.github.seoj17.data.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.seoj17.data.local.SummonerDatabase
import io.github.seoj17.data.local.bookmark.SummonerBookmarkDao
import io.github.seoj17.data.local.champions.ChampionsDao
import io.github.seoj17.data.local.match.MatchInfoDao
import io.github.seoj17.data.local.perks.PerkDao
import io.github.seoj17.data.local.recent.search.RecentSearchDao
import io.github.seoj17.data.local.summoner.SummonerInfoDao
import io.github.seoj17.data.local.user.RegisterUserInfoDao
import io.github.seoj17.data.local.user.RegisterUserMostChampDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context): SummonerDatabase {
        return Room.databaseBuilder(
            appContext,
            SummonerDatabase::class.java,
            "summoner.db",
        ).build()
    }

    @Singleton
    @Provides
    fun provideRecentSearchDao(database: SummonerDatabase): RecentSearchDao {
        return database.recentSearchDao()
    }

    @Singleton
    @Provides
    fun provideSummonerBookmarkDao(database: SummonerDatabase): SummonerBookmarkDao {
        return database.summonerBookmarkDao()
    }

    @Singleton
    @Provides
    fun provideChampionsDao(database: SummonerDatabase): ChampionsDao {
        return database.championsDao()
    }

    @Singleton
    @Provides
    fun providePerksDao(database: SummonerDatabase): PerkDao {
        return database.perksDao()
    }

    @Singleton
    @Provides
    fun provideRegisterUserInfoDao(database: SummonerDatabase): RegisterUserInfoDao {
        return database.registerUserInfoDao()
    }

    @Singleton
    @Provides
    fun provideRegisterUserMostChampDao(database: SummonerDatabase): RegisterUserMostChampDao {
        return database.registerUserMostChampDao()
    }

    @Singleton
    @Provides
    fun provideMatchInfoDao(database: SummonerDatabase): MatchInfoDao {
        return database.matchInfoDao()
    }

    @Singleton
    @Provides
    fun provideSummonerInfoDao(database: SummonerDatabase): SummonerInfoDao {
        return database.summonerInfoDao()
    }
}
