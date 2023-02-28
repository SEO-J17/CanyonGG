package io.github.seoj17.canyongg.ui.adapter

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.github.seoj17.canyongg.contract.UrlContract
import io.github.seoj17.canyongg.domain.model.DomainBookmarkSummoner
import io.github.seoj17.canyongg.domain.model.DomainMatches
import io.github.seoj17.canyongg.domain.model.DomainRecentSummoner
import io.github.seoj17.canyongg.ui.detail.analysisTab.pages.AnalysisPageListAdapter
import io.github.seoj17.canyongg.ui.detail.summaryTab.LoseParticipantsListAdapter
import io.github.seoj17.canyongg.ui.detail.summaryTab.WinParticipantsListAdapter
import io.github.seoj17.canyongg.ui.home.BookmarkListAdapter
import io.github.seoj17.canyongg.ui.home.RotationChampListAdapter
import io.github.seoj17.canyongg.ui.model.RecentSummoners
import io.github.seoj17.canyongg.ui.model.SummonerBookmark
import io.github.seoj17.canyongg.ui.model.SummonerMatchRecord
import io.github.seoj17.canyongg.ui.record.RecordListAdapter
import io.github.seoj17.canyongg.ui.search.SearchSummonerListAdapter
import io.github.seoj17.canyongg.utils.TimeFormatter
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
fun ImageView.setSummonerRankEmblem(tier: String?) {
    Glide
        .with(this.context)
        .load(tier?.rankEmblemResId())
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
    (adapter as? SearchSummonerListAdapter)?.submitList(
        RecentSummoners(recentSummoners ?: emptyList())
    )
}

@BindingAdapter("bind:winParticipantsList")
fun RecyclerView.setWinParticipantsList(participantsMatches: List<SummonerMatchRecord>?) {
    (adapter as? WinParticipantsListAdapter)?.submitList(participantsMatches ?: emptyList())
}

@BindingAdapter("bind:loseParticipantsList")
fun RecyclerView.setLoseParticipantsList(participantsMatches: List<SummonerMatchRecord>?) {
    (adapter as? LoseParticipantsListAdapter)?.submitList(participantsMatches ?: emptyList())
}

@BindingAdapter("bind:bookmarkSummonersList")
fun RecyclerView.setBookmarkSummonersList(bookmarkSummoners: List<DomainBookmarkSummoner>?) {
    (adapter as? BookmarkListAdapter)?.submitList(
        SummonerBookmark(bookmarkSummoners ?: emptyList())
    )
}

@BindingAdapter("bind:teamAnalysisList")
fun RecyclerView.setTeamAnalysisList(summonerRecord: List<SummonerMatchRecord>?) {
    (adapter as? AnalysisPageListAdapter)?.submitList(summonerRecord ?: emptyList())
}

@BindingAdapter("bind:rotationChampList")
fun RecyclerView.setRotationChampList(champions: List<String>?) {
    (adapter as? RotationChampListAdapter)?.submitList(champions ?: emptyList())
}

@BindingAdapter("bind:matchPlayedTime")
fun TextView.setMatchPlayedTime(time: Int) {
    this.text = TimeFormatter.formatTime(time * 1000)
}

@BindingAdapter("bind:matchDate")
fun TextView.setMatchDate(date: Long) {
    this.text = TimeFormatter.formatTime(date)
}

@BindingAdapter("bind:championImage")
fun ImageView.setChampionImage(champion: String) {
    Glide
        .with(this.context)
        .load(String.format(UrlContract.CHAMPION_URL, champion))
        .into(this)
}

@BindingAdapter("bind:spell")
fun ImageView.setSpell(spell: String) {
    Glide
        .with(this.context)
        .load(String.format(UrlContract.SPELL_URL, spell))
        .into(this)
}

@BindingAdapter("bind:item")
fun ImageView.setItems(item: Int) {
    Glide
        .with(this.context)
        .load(String.format(UrlContract.ITEM_URL, item))
        .into(this)
}

@BindingAdapter("bind:rune")
fun ImageView.setRunes(rune: String) {
    Glide
        .with(this.context)
        .load("${UrlContract.RUNE_URL}${rune}")
        .into(this)
}