package io.github.seoj17.canyongg.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.bumptech.glide.Glide
import io.github.seoj17.canyongg.databinding.SummonerInfoCustomBinding
import java.lang.String.format

@BindingMethods(
    value = [
        BindingMethod(
            type = SummonerInfoView::class,
            attribute = "userThumbnail",
            method = "setUserThumbnail"
        ),
        BindingMethod(
            type = SummonerInfoView::class,
            attribute = "userLevel",
            method = "setUserLevel"
        ),
        BindingMethod(
            type = SummonerInfoView::class,
            attribute = "userName",
            method = "setUserName"
        ),
        BindingMethod(
            type = SummonerInfoView::class,
            attribute = "userTier",
            method = "setUserTier"
        ),
        BindingMethod(
            type = SummonerInfoView::class,
            attribute = "refreshButton",
            method = "setRefreshButton"
        ),
        BindingMethod(
            type = SummonerInfoView::class,
            attribute = "infoDeleteButton",
            method = "setDeleteButton"
        ),
        BindingMethod(
            type = SummonerInfoView::class,
            attribute = "userWin",
            method = "setUserWin"
        ),
        BindingMethod(
            type = SummonerInfoView::class,
            attribute = "userLose",
            method = "setUserLose"
        ),
        BindingMethod(
            type = SummonerInfoView::class,
            attribute = "userWinRate",
            method = "setUserWinRate"
        ),
        BindingMethod(
            type = SummonerInfoView::class,
            attribute = "userKda",
            method = "setUserKda"
        ),
    ]
)

class SummonerInfoView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private val binding =
        SummonerInfoCustomBinding.inflate(LayoutInflater.from(context), this, true)

    fun setUserThumbnail(imgId: Int?) {
        val imgUrl = "https://ddragon.leagueoflegends.com/cdn/13.1.1/img/profileicon/"
        Glide.with(binding.mainUserThumbNail.context)
            .load("${imgUrl}${imgId}.png").into(binding.mainUserThumbNail)
    }

    fun setUserLevel(level: Int?) {
        binding.mainUserLevel.text = level.toString()
    }

    fun setUserName(name: String?) {
        binding.mainUserName.text = name
    }

    fun setUserTier(tier: String) {
        binding.mainUserTier.text = tier
    }

    fun setUserWin(win: Int) {
        binding.userWin.text = "${win}승 "
    }

    fun setUserLose(lose: Int) {
        binding.userLose.text = "${lose}패 "
    }

    fun setUserWinRate(winRate: Int) {
        binding.userWinScore.text = "승률 ${winRate}% "
    }

    fun setUserKda(kda: Double) {
        binding.userKda.text = "${format("%.2f", kda)} : 1"
    }
}