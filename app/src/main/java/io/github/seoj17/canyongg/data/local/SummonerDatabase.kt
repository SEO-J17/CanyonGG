package io.github.seoj17.canyongg.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.seoj17.canyongg.data.local.bookmark.SummonerBookmarkDao
import io.github.seoj17.canyongg.data.local.bookmark.SummonerBookmarkEntity
import io.github.seoj17.canyongg.data.local.champions.ChampionsDao
import io.github.seoj17.canyongg.data.local.champions.ChampionsEntity
import io.github.seoj17.canyongg.data.local.match.MatchInfoDao
import io.github.seoj17.canyongg.data.local.match.MatchInfoEntity
import io.github.seoj17.canyongg.data.local.perks.PerksDao
import io.github.seoj17.canyongg.data.local.perks.PerksEntity
import io.github.seoj17.canyongg.data.local.recent.search.RecentSearchDao
import io.github.seoj17.canyongg.data.local.recent.search.RecentSearchNameEntity
import io.github.seoj17.canyongg.data.local.summoner.SummonerInfoDao
import io.github.seoj17.canyongg.data.local.summoner.SummonerInfoEntity
import io.github.seoj17.canyongg.data.local.user.MyMostChampDao
import io.github.seoj17.canyongg.data.local.user.MyMostChampEntity
import io.github.seoj17.canyongg.data.local.user.MyUserInfoDao
import io.github.seoj17.canyongg.data.local.user.MyUserInfoEntity

@Database(
    entities = [
        RecentSearchNameEntity::class,
        SummonerBookmarkEntity::class,
        ChampionsEntity::class,
        PerksEntity::class,
        MyUserInfoEntity::class,
        MyMostChampEntity::class,
        MatchInfoEntity::class,
        SummonerInfoEntity::class,
    ],
    version = 1
)
abstract class SummonerDatabase : RoomDatabase() {
    abstract fun recentSearchDao(): RecentSearchDao
    abstract fun summonerBookmarkDao(): SummonerBookmarkDao
    abstract fun championsDao(): ChampionsDao
    abstract fun perksDao(): PerksDao
    abstract fun MyUserInfoDao(): MyUserInfoDao
    abstract fun myMostChampDao(): MyMostChampDao
    abstract fun matchInfoDao(): MatchInfoDao
    abstract fun summonerInfoDao(): SummonerInfoDao
}