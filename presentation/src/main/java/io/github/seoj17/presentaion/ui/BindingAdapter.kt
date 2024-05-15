package io.github.seoj17.presentaion.ui

import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.google.android.material.textfield.TextInputLayout
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.contract.UrlContract
import io.github.seoj17.presentaion.model.Champion
import io.github.seoj17.presentaion.model.ChampionBookmark
import io.github.seoj17.presentaion.model.GameModeState
import io.github.seoj17.presentaion.model.MostKillState
import io.github.seoj17.presentaion.model.RecentSummoners
import io.github.seoj17.presentaion.model.RegisterUserInfo
import io.github.seoj17.presentaion.model.RotationChamp
import io.github.seoj17.presentaion.model.SummonerBookmark
import io.github.seoj17.presentaion.model.SummonerMatchRecord
import io.github.seoj17.presentaion.ui.champion.ChampionListAdapter
import io.github.seoj17.presentaion.ui.detail.analysisTab.pages.AnalysisPageListAdapter
import io.github.seoj17.presentaion.ui.detail.summaryTab.LoseParticipantsListAdapter
import io.github.seoj17.presentaion.ui.detail.summaryTab.WinParticipantsListAdapter
import io.github.seoj17.presentaion.ui.home.BookmarkChampionListAdapter
import io.github.seoj17.presentaion.ui.home.BookmarkListAdapter
import io.github.seoj17.presentaion.ui.home.RotationChampListAdapter
import io.github.seoj17.presentaion.ui.record.RecordListAdapter
import io.github.seoj17.presentaion.ui.search.SearchSummonerListAdapter
import io.github.seoj17.presentaion.utils.NumberFormatter
import io.github.seoj17.presentaion.utils.TimeFormatter
import io.github.seoj17.presentaion.utils.coroutineScope
import io.github.seoj17.presentaion.utils.rankEmblemResId
import kotlinx.coroutines.launch

@BindingAdapter("bind:summonerProfile")
fun ImageView.setSummonerProfile(id: Int) {
    Glide
        .with(this.context)
        .load(UrlContract.profileIconUrl(id))
        .circleCrop()
        .into(this)
}

@BindingAdapter("bind:summonerRankEmblem")
fun ImageView.setSummonerRankEmblem(tier: String?) {
    Glide
        .with(this.context)
        .load(tier?.rankEmblemResId())
        .transform(CenterCrop(), RoundedCorners(16))
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

@BindingAdapter("bind:bookmarkChampionList")
fun RecyclerView.setBookmarkChampionList(bookmarkChampion: List<ChampionBookmark>?) {
    (adapter as? BookmarkChampionListAdapter)?.submitList(bookmarkChampion ?: emptyList())
}

@BindingAdapter("bind:teamAnalysisList")
fun RecyclerView.setTeamAnalysisList(summonerRecord: List<SummonerMatchRecord>?) {
    (adapter as? AnalysisPageListAdapter)?.submitList(summonerRecord ?: emptyList())
}

@BindingAdapter("bind:rotationChampList")
fun RecyclerView.setRotationChampList(champions: List<RotationChamp>) {
    (adapter as? RotationChampListAdapter)?.submitList(champions)
}

@BindingAdapter("bind:champList")
fun RecyclerView.setChampList(champions: List<Champion>) {
    (adapter as? ChampionListAdapter)?.submitList(champions)
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
fun ImageView.setChampionImage(champion: String?) {
    Glide
        .with(this.context)
        .load(UrlContract.championUrl(champion ?: ""))
        .transform(CenterCrop(), RoundedCorners(16))
        .placeholder(R.drawable.default_img)
        .into(this)
}

@BindingAdapter("bind:spell")
fun ImageView.setSpell(spell: String) {
    Glide
        .with(this.context)
        .load(UrlContract.spellUrl(spell))
        .transform(CenterCrop(), RoundedCorners(16))
        .placeholder(R.drawable.default_img)
        .into(this)
}

@BindingAdapter("bind:item")
fun ImageView.setItems(item: Int) {
    Glide
        .with(this.context)
        .load(UrlContract.itemUrl(item))
        .transform(CenterCrop(), RoundedCorners(16))
        .placeholder(R.drawable.default_img)
        .into(this)
}

@BindingAdapter("bind:rune")
fun ImageView.setRunes(rune: String) {
    Glide
        .with(this.context)
        .load("${UrlContract.RUNE_URL}$rune")
        .transform(CenterCrop(), RoundedCorners(16))
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

@BindingAdapter("bind:splashChamp")
fun ImageView.setSplashChamp(name: String) {
    Glide
        .with(this.context)
        .load(UrlContract.splashChampionUrl(name))
        .transform(CenterCrop(), RoundedCorners(16))
        .placeholder(R.drawable.default_img)
        .into(this)
}

@BindingAdapter("bind:kdaTextColor")
fun TextView.setKdaTextColor(kda: Double) {
    val color =
        if (kda < 3.0) {
            R.color.low_kda_color
        } else if (kda < 5.0) {
            R.color.normal_kda_color
        } else if (kda < 10.0) {
            R.color.death_color
        } else {
            R.color.high_kda_color
        }
    setTextColor(
        context.getColor(color),
    )
}

@BindingAdapter("bind:winRateTextColor")
fun TextView.setWinRateText(winRate: Int) {
    val color =
        if (winRate < 30) {
            R.color.low_win_rate_color
        } else if (winRate < 50) {
            R.color.normal_win_rate_color
        } else if (winRate < 80) {
            R.color.middle_win_rate_color
        } else {
            R.color.high_win_rate_color
        }
    setTextColor(
        context.getColor(color),
    )
}

@BindingAdapter("bind:highestKillTextColor")
fun TextView.setHighestKillText(kill: Int) {
    val color =
        when (MostKillState(kill)) {
            MostKillState.ZERO -> R.color.low_kda_color
            MostKillState.SINGLE -> R.color.low_kda_color
            MostKillState.DOUBLE -> R.color.normal_kda_color
            MostKillState.TRIPLE -> R.color.middle_kda_color
            MostKillState.QUADRA -> R.color.high_kda_color
            MostKillState.PENTA -> R.color.penta_kill_color
        }

    setTextColor(context.getColor(color))
}

@BindingAdapter("bind:loadingVisibility")
fun ProgressBar.setLoadingVisibility(isLoading: Boolean) {
    isVisible = isLoading
}

@BindingAdapter("bind:loadingState", "bind:userInfoState", requireAll = true)
fun View.setRegisterTabVisibility(
    isLoading: Boolean,
    userInfo: RegisterUserInfo?,
) {
    isVisible =
        if (isLoading && (userInfo == null)) {
            false
        } else if (!isLoading && (userInfo != null)) {
            false
        } else {
            !isLoading
        }
}

@BindingAdapter("bind:searchSubmit")
fun SearchView.setSearchSubmit(searchQuery: (String?) -> Unit) {
    setOnQueryTextListener(
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchQuery(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        },
    )
}
