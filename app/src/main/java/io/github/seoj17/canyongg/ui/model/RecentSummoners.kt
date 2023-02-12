package io.github.seoj17.canyongg.ui.model

import androidx.recyclerview.widget.DiffUtil
import io.github.seoj17.canyongg.domain.model.DomainRecentSummoner

data class RecentSummoners(
    val puuid: String,
    val name: String,
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<RecentSummoners>() {
            override fun areItemsTheSame(
                oldItem: RecentSummoners,
                newItem: RecentSummoners
            ): Boolean {
                return oldItem.puuid == newItem.puuid
            }

            override fun areContentsTheSame(
                oldItem: RecentSummoners,
                newItem: RecentSummoners
            ): Boolean {
                return oldItem == newItem
            }
        }

        operator fun invoke(domain: DomainRecentSummoner): RecentSummoners {
            return RecentSummoners(
                puuid = domain.puuid,
                name = domain.name,
            )
        }

        operator fun invoke(domain: List<DomainRecentSummoner>): List<RecentSummoners> {
            return domain.map {
                invoke(it)
            }
        }
    }
}