package io.github.seoj17.canyongg.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.seoj17.canyongg.data.local.bookmark.SummonerBookmarkDao
import io.github.seoj17.canyongg.data.local.bookmark.SummonerBookmarkEntity
import io.github.seoj17.canyongg.data.local.champions.ChampionsDao
import io.github.seoj17.canyongg.data.local.champions.ChampionsEntity
import io.github.seoj17.canyongg.data.local.match.MatchInfoDao
import io.github.seoj17.canyongg.data.local.match.MatchInfoEntity
import io.github.seoj17.canyongg.data.local.perks.PerkDao
import io.github.seoj17.canyongg.data.local.perks.PerkEntity
import io.github.seoj17.canyongg.data.local.recent.search.RecentSearchDao
import io.github.seoj17.canyongg.data.local.recent.search.RecentSearchNameEntity
import io.github.seoj17.canyongg.data.local.summoner.SummonerInfoDao
import io.github.seoj17.canyongg.data.local.summoner.SummonerInfoEntity
import io.github.seoj17.canyongg.data.local.user.RegisterUserMostChampDao
import io.github.seoj17.canyongg.data.local.user.RegisterUserMostChampEntity
import io.github.seoj17.canyongg.data.local.user.RegisterUserInfoDao
import io.github.seoj17.canyongg.data.local.user.RegisterUserInfoEntity

@Database(
    entities = [
        RecentSearchNameEntity::class,
        SummonerBookmarkEntity::class,
        ChampionsEntity::class,
        PerkEntity::class,
        RegisterUserInfoEntity::class,
        RegisterUserMostChampEntity::class,
        MatchInfoEntity::class,
        SummonerInfoEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
abstract class SummonerDatabase : RoomDatabase() {
    abstract fun recentSearchDao(): RecentSearchDao
    abstract fun summonerBookmarkDao(): SummonerBookmarkDao
    abstract fun championsDao(): ChampionsDao
    abstract fun perksDao(): PerkDao
    abstract fun registerUserInfoDao(): RegisterUserInfoDao
    abstract fun registerUserMostChampDao(): RegisterUserMostChampDao
    abstract fun matchInfoDao(): MatchInfoDao
    abstract fun summonerInfoDao(): SummonerInfoDao
}