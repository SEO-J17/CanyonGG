package io.github.seoj17.canyongg.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.seoj17.canyongg.domain.model.DomainMatches
import io.github.seoj17.canyongg.domain.model.DomainRecentSummoner
import io.github.seoj17.canyongg.ui.detail.summaryTab.LoseParticipantsListAdapter
import io.github.seoj17.canyongg.ui.detail.summaryTab.WinParticipantsListAdapter
import io.github.seoj17.canyongg.ui.model.ParticipantsMatches
import io.github.seoj17.canyongg.ui.model.RecentSummoners
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import io.github.seoj17.canyongg.ui.record.RecordListAdapter
import io.github.seoj17.canyongg.ui.search.SearchSummonerListAdapter
import io.github.seoj17.canyongg.utils.coroutineScope
import io.github.seoj17.canyongg.utils.rankEmblemResId
import kotlinx.coroutines.launch

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

@BindingAdapter("bind:recentSummonerList")
fun RecyclerView.setRecentSummonerList(recentSummoners: List<DomainRecentSummoner>?) {
    val list = recentSummoners ?: emptyList()
    (adapter as? SearchSummonerListAdapter)?.submitList(RecentSummoners(list))
}

@BindingAdapter("bind:winParticipantsList")
fun RecyclerView.setWinParticipantsList(participantsMatches: List<ParticipantsMatches>?) {
    val list = participantsMatches ?: emptyList()
    (adapter as? WinParticipantsListAdapter)?.submitList(participantsMatches)
}

@BindingAdapter("bind:loseParticipantsList")
fun RecyclerView.setLoseParticipantsList(participantsMatches: List<ParticipantsMatches>?) {
    val list = participantsMatches ?: emptyList()
    (adapter as? LoseParticipantsListAdapter)?.submitList(participantsMatches)
}
