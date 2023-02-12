package io.github.seoj17.canyongg.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.canyongg.databinding.ItemRecentNameBinding
import io.github.seoj17.canyongg.ui.model.RecentSummoners

class SearchSummonerListViewHolder(
    private val binding: ItemRecentNameBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(recent: RecentSummoners) {
        with(binding) {
            recentSummonerItem.text = recent.name
        }
    }

    fun getDeleteButton(): ImageView {
        return binding.deleteSummoner
    }

    companion object {
        operator fun invoke(parent: ViewGroup): SearchSummonerListViewHolder {
            return SearchSummonerListViewHolder(
                ItemRecentNameBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }
    }
}