package io.github.seoj17.canyongg.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.bumptech.glide.Glide
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.data.model.Summoner
import io.github.seoj17.canyongg.databinding.ViewUserInfoBinding
import io.github.seoj17.canyongg.ui.model.UserRecord

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
                userProfileId = it.profileIconId
                userLevel = it.summonerLevel
                userName = it.name
            }
            field = value
        }

    var userProfileId: Int? = null
        set(value) {
            setUserThumbnail(value)
            field = value
        }

    private fun setUserThumbnail(imgId: Int?) {
        val imgUrl = "https://ddragon.leagueoflegends.com/cdn/13.1.1/img/profileicon/"
        Glide
            .with(binding.mainUserThumbNail.context)
            .load("${imgUrl}${imgId}.png")
            .into(binding.mainUserThumbNail)
    }

    var userLevel: Int = 0
        set(value) {
            binding.mainUserLevel.text = "$value"
            field = value
        }

    var userName: CharSequence = ""
        set(value) {
            binding.mainUserName.text = value
            field = value
        }


    var userTier: CharSequence = ""
        set(value) {
            binding.mainUserTier.text = value
            field = value
        }

    var userRecord: UserRecord? = null
        set(value) {
            value?.let {
                userMatches = it.wholeMatch
                userWin = it.winCount
                userLose = it.loseCount
                userWinRate = it.winRate
                userKda = it.kda
            }
            field = value
        }

    var userMatches: Int = 0
        set(value) {
            binding.userMatches.text = resources.getString(R.string.user_view_whole_match, value)
            field = value
        }

    var userWin: Int = 0
        set(value) {
            binding.userWin.text = resources.getString(R.string.user_view_win_count, value)
            field = value
        }

    var userLose: Int = 0
        set(value) {
            binding.userLose.text = resources.getString(R.string.user_view_lose_count, value)
            field = value
        }

    var userWinRate: Int = 0
        set(value) {
            binding.userWinScore.text = resources.getString(R.string.user_view_win_rate, value)
            field = value
        }

    var userKda: Double = 0.0
        set(value) {
            binding.userKda.text = resources.getString(R.string.user_view_kda, value)
            field = value
        }
}