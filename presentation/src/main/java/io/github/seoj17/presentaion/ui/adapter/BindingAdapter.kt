package io.github.seoj17.presentaion.ui.adapter

import android.view.View
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.android.material.textfield.TextInputLayout
import io.github.seoj17.presentaion.contract.UrlContract
import io.github.seoj17.presentaion.model.RecentSummoners
import io.github.seoj17.presentaion.model.RotationChamp
import io.github.seoj17.presentaion.model.SummonerBookmark
import io.github.seoj17.presentaion.model.SummonerMatchRecord
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPageListAdapter
import io.github.seoj17.presentaion.ui.detail.summaryTab.LoseParticipantsListAdapter
import io.github.seoj17.presentaion.ui.detail.summaryTab.WinParticipantsListAdapter
import io.github.seoj17.presentaion.ui.home.BookmarkListAdapter
import io.github.seoj17.presentaion.ui.home.RotationChampListAdapter
import io.github.seoj17.presentaion.ui.record.RecordListAdapter
import io.github.seoj17.presentaion.ui.search.SearchSummonerListAdapter
import io.github.seoj17.presentaion.ui.state.UiState
import io.github.seoj17.presentaion.utils.NumberFormatter
import io.github.seoj17.presentaion.utils.TimeFormatter
import io.github.seoj17.presentaion.utils.coroutineScope
import io.github.seoj17.presentaion.utils.rankEmblemResId
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@BindingAdapter("bind:summonerProfile")
fun ImageView.setSummonerProfile(id: Int) {
    Glide
        .with(this.context)
        .load(UrlContract.profileIconUrl(id))
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
fun RecyclerView.setHistoryList(history: PagingData<SummonerMatchRecord>) {
    coroutineScope?.launch {
        (adapter as? RecordListAdapter)?.submitData(history)
    }
}

@BindingAdapter("bind:recentSummonerList")
fun RecyclerView.setRecentSummonerList(recentSummoners: List<RecentSummoners>?) {
    (adapter as? SearchSummonerListAdapter)?.submitList(
        recentSummoners ?: emptyList(),
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
fun RecyclerView.setBookmarkSummonersList(bookmarkSummoners: List<SummonerBookmark>?) {
    (adapter as? BookmarkListAdapter)?.submitList(
        bookmarkSummoners ?: emptyList(),
    )
}

@BindingAdapter("bind:teamAnalysisList")
fun RecyclerView.setTeamAnalysisList(summonerRecord: List<SummonerMatchRecord>?) {
    (adapter as? AnalysisPageListAdapter)?.submitList(summonerRecord ?: emptyList())
}

@BindingAdapter("bind:rotationChampList")
fun RecyclerView.setRotationChampList(champions: List<RotationChamp>?) {
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
        .load(UrlContract.championUrl(champion))
        .into(this)
}

@BindingAdapter("bind:spell")
fun ImageView.setSpell(spell: String) {
    Glide
        .with(this.context)
        .load(UrlContract.spellUrl(spell))
        .into(this)
}

@BindingAdapter("bind:item")
fun ImageView.setItems(item: Int) {
    Glide
        .with(this.context)
        .load(UrlContract.itemUrl(item))
        .into(this)
}

@BindingAdapter("bind:rune")
fun ImageView.setRunes(rune: String) {
    Glide
        .with(this.context)
        .load("${UrlContract.RUNE_URL}$rune")
        .into(this)
}

@BindingAdapter("bind:mostKill")
fun TextView.setMostKill(kill: Int) {
    this.setText(MostKillState(kill).killName)
}

@BindingAdapter("bind:bookmarkState")
fun AppCompatButton.setBookmarkState(isBookmarked: Boolean?) {
    isBookmarked?.let {
        this.isSelected = it
    }
}

@BindingAdapter("bind:analysisValue")
fun TextView.setAnalysisValue(value: Int) {
    this.text = NumberFormatter.formatNumber(value)
}

@BindingAdapter("bind:gameMode")
fun TextView.setGameMode(mode: String) {
    this.setText(GameModeState(mode).modeName)
}

@BindingAdapter("bind:valid", "bind:errorText", requireAll = true)
fun TextInputLayout.validInputText(
    isValid: Boolean,
    message: String,
) {
    if (isValid) {
        error = null
        isErrorEnabled = false
    } else {
        if (editText?.text?.isNotEmpty() == true) {
            isErrorEnabled = true
            error = message
        } else {
            isErrorEnabled = false
        }
    }
}

@BindingAdapter("bind:check")
fun RadioGroup.setCheckedTheme(index: Int) {
    this.check(getChildAt(index).id)
}

@BindingAdapter("bind:loadingState")
fun ShimmerFrameLayout.setLoadingState(state: StateFlow<UiState>) {
    visibility = when (state.value) {
        is UiState.Loading -> {
            startShimmer()
            View.VISIBLE
        }
        is UiState.Success -> {
            stopShimmer()
            View.GONE
        }
        is UiState.Empty -> {
            View.GONE
        }
    }
}
