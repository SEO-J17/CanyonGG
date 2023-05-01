package io.github.seoj17.presentation.model

import androidx.recyclerview.widget.DiffUtil
import io.github.seoj17.domain.model.BookmarkSummonerDomainModel

data class SummonerBookmark(
    val summonerPuuid: String,
    val summonerName: String,
    val summonerLevel: Int,
    val summonerIcon: Int,
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<SummonerBookmark>() {
            override fun areItemsTheSame(
                oldItem: SummonerBookmark,
                newItem: SummonerBookmark,
            ): Boolean {
                return oldItem.summonerPuuid == newItem.summonerPuuid
            }

            override fun areContentsTheSame(
                oldItem: SummonerBookmark,
                newItem: SummonerBookmark,
            ): Boolean {
                return oldItem == newItem
            }
        }

        operator fun invoke(domain: BookmarkSummonerDomainModel): SummonerBookmark {
            return SummonerBookmark(
                summonerPuuid = domain.puuid,
                summonerName = domain.summonerName,
                summonerLevel = domain.summonerLevel,
                summonerIcon = domain.summonerIcon,
            )
        }

        operator fun invoke(domain: List<BookmarkSummonerDomainModel>): List<SummonerBookmark> {
            return domain.map {
                invoke(it)
            }
        }

        fun toDomainModel(summoner: SummonerInfo): BookmarkSummonerDomainModel {
            return BookmarkSummonerDomainModel(
                puuid = summoner.puuid,
                summonerName = summoner.name,
                summonerLevel = summoner.level,
                summonerIcon = summoner.profile,
            )
        }
    }
}
