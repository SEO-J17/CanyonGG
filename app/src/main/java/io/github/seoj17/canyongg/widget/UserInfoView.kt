package io.github.seoj17.canyongg.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.bumptech.glide.Glide
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.databinding.ViewUserInfoBinding
import io.github.seoj17.canyongg.ui.model.UserRecord
import java.lang.String.format

@BindingMethods(
    value = [
        BindingMethod(
            type = UserInfoView::class,
            attribute = "userInfo",
            method = "setUserInfo"
        ),
        BindingMethod(
            type = UserInfoView::class,
            attribute = "userTier",
            method = "setUserTier"
        ),
        BindingMethod(
            type = UserInfoView::class,
            attribute = "userRecord",
            method = "setUserRecord"
        ),
    ]
)

class UserInfoView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private val binding =
        ViewUserInfoBinding.inflate(LayoutInflater.from(context), this, true)

    var userInfo: Summoner? = null
        set(value) {
            value?.let {
                setUserThumbnail(it.profileIconId)
                setUserLevel(it.summonerLevel)
                setUserName(it.name)
            }
            field = value
        }

    var userTier: CharSequence
        get() = binding.mainUserTier.text
        set(value) {
            binding.mainUserTier.text = value
        }

    var userRecord: UserRecord? = null
        set(value) {
            value?.let {
                setUserMatches(it.wholeMatch)
                setUserWin(it.winCount)
                setUserLose(it.loseCount)
                setUserWinRate(it.winRate)
                setUserKda(it.kda)
            }
            field = value
        }

    private fun setUserMatches(matches: Int) {
        binding.userMatches.text = "${matches}전 "
    }

    private fun setUserThumbnail(imgId: Int?) {
        val imgUrl = "https://ddragon.leagueoflegends.com/cdn/13.1.1/img/profileicon/"
        Glide.with(binding.mainUserThumbNail.context)
            .load("${imgUrl}${imgId}.png").into(binding.mainUserThumbNail)
    }

    private fun setUserLevel(level: Int?) {
        binding.mainUserLevel.text = level.toString()
    }

    private fun setUserName(name: String?) {
        binding.mainUserName.text = name
    }

    private fun setUserWin(win: Int) {
        binding.userWin.text = "${win}승 "
    }

    private fun setUserLose(lose: Int) {
        binding.userLose.text = "${lose}패 "
    }

    private fun setUserWinRate(winRate: Int) {
        binding.userWinScore.text = "승률 ${winRate}% "
    }

    private fun setUserKda(kda: Double) {
        binding.userKda.text = "KDA : ${format("%.2f", kda)} : 1"
    }
}