package io.github.seoj17.canyongg.adapter

import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.seoj17.canyongg.domain.model.DomainMatches
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import io.github.seoj17.canyongg.ui.search.RecordListAdapter
import io.github.seoj17.canyongg.utils.coroutineScope
import io.github.seoj17.canyongg.utils.rankEmblemResId
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@BindingAdapter("bind:setOnQueryTextListener")
fun SearchView.setOnQueryText(searchSummoner: (String?) -> Job) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            searchSummoner(query)
            return false
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            return false
        }
    })
}

@BindingAdapter("bind:summonerProfile")
fun ImageView.setSummonerProfile(id: Int) {
    val imgUrl = "https://ddragon.leagueoflegends.com/cdn/13.3.1/img/profileicon/"
    Glide
        .with(this.context)
        .load("${imgUrl}${id}.png")
        .into(this)
}

@BindingAdapter("bind:summonerRankEmblem")
fun ImageView.setSummonerRankEmblem(tier: String) {
    val rank = tier.rankEmblemResId()
    Glide
        .with(this.context)
        .load(rank)
        .into(this)
}

@BindingAdapter("bind:historyList")
fun RecyclerView.setHistoryList(history: PagingData<DomainMatches>) {
    coroutineScope?.launch {
        (adapter as? RecordListAdapter)?.submitData(SummonerMatchRecord(history))
    }
}

