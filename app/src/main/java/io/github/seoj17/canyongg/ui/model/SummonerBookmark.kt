package io.github.seoj17.canyongg.ui.model

import androidx.recyclerview.widget.DiffUtil
import io.github.seoj17.canyongg.domain.model.DomainBookmarkSummoner


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
                newItem: SummonerBookmark
            ): Boolean {
                return oldItem.summonerPuuid == newItem.summonerPuuid
            }

            override fun areContentsTheSame(
                oldItem: SummonerBookmark,
                newItem: SummonerBookmark
            ): Boolean {
                return oldItem == newItem
            }
        }

        operator fun invoke(domain: DomainBookmarkSummoner): SummonerBookmark {
            return SummonerBookmark(
                summonerPuuid = domain.summonerPuuid,
                summonerName = domain.summonerName,
                summonerLevel = domain.summonerLevel,
                summonerIcon = domain.summonerIcon,
            )
        }

        operator fun invoke(domain: List<DomainBookmarkSummoner>): List<SummonerBookmark> {
            return domain.map {
                invoke(it)
            }
        }
    }
}