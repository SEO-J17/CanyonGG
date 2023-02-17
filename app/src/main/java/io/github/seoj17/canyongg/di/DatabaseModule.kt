package io.github.seoj17.canyongg.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.seoj17.canyongg.data.local.RecentSearchDao
import io.github.seoj17.canyongg.data.local.SummonerBookmarkDao
import io.github.seoj17.canyongg.data.local.SummonerDatabase
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
            "summoner.db"
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
}