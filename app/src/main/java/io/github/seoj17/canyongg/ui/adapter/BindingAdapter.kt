package io.github.seoj17.canyongg.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.seoj17.canyongg.contract.UrlContract
import io.github.seoj17.canyongg.domain.model.DomainBookmarkSummoner
import io.github.seoj17.canyongg.domain.model.DomainMatches
import io.github.seoj17.canyongg.domain.model.DomainRecentSummoner
import io.github.seoj17.canyongg.ui.detail.summaryTab.LoseParticipantsListAdapter
import io.github.seoj17.canyongg.ui.detail.summaryTab.WinParticipantsListAdapter
import io.github.seoj17.canyongg.ui.home.BookmarkListAdapter
import io.github.seoj17.canyongg.ui.model.ParticipantsMatches
import io.github.seoj17.canyongg.ui.model.RecentSummoners
import io.github.seoj17.canyongg.ui.model.SummonerBookmark
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import io.github.seoj17.canyongg.ui.record.RecordListAdapter
import io.github.seoj17.canyongg.ui.search.SearchSummonerListAdapter
import io.github.seoj17.canyongg.utils.coroutineScope
import io.github.seoj17.canyongg.utils.rankEmblemResId
import kotlinx.coroutines.launch

@BindingAdapter("bind:summonerProfile")
fun ImageView.setSummonerProfile(id: Int) {
    Glide
        .with(this.context)
        .load(String.format(UrlContract.PROFILE_ICON_URL, id))
        .into(this)
}

@BindingAdapter("bind:summonerRankEmblem")
fun ImageView.setSummonerRankEmblem(tier: String) {
    Glide
        .with(this.context)
        .load(tier.rankEmblemResId())
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
    (adapter as? WinParticipantsListAdapter)?.submitList(list)
}

@BindingAdapter("bind:loseParticipantsList")
fun RecyclerView.setLoseParticipantsList(participantsMatches: List<ParticipantsMatches>?) {
    val list = participantsMatches ?: emptyList()
    (adapter as? LoseParticipantsListAdapter)?.submitList(list)
}

@BindingAdapter("bind:bookmarkSummonersList")
fun RecyclerView.setBookmarkSummonersList(bookmarkSummoners: List<DomainBookmarkSummoner>?) {
    val list = bookmarkSummoners ?: emptyList()
    (adapter as? BookmarkListAdapter)?.submitList(SummonerBookmark(list))
}
