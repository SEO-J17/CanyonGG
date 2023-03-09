package io.github.seoj17.canyongg.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.bumptech.glide.Glide
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.contract.UrlContract
import io.github.seoj17.canyongg.databinding.ViewUserInfoBinding
import io.github.seoj17.canyongg.ui.model.RegisterUserInfo

@BindingMethods(
    value = [
        BindingMethod(
            type = UserInfoView::class,
            attribute = "userInfo",
            method = "setUserInfo"
        ),
    ]
)

class UserInfoView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private val binding = ViewUserInfoBinding.inflate(LayoutInflater.from(context), this, true)

    var userInfo: RegisterUserInfo? = null
        set(value) {
            value?.let {
                userProfileId = it.profile
                userLevel = it.level
                userName = it.name
                userMatches = it.wholeMatch
                userWin = it.win
                userLose = it.lose
                userWinRate = it.winRate
                userTier = it.tier
                userKda = it.kda
            }
            field = value
        }

    var userProfileId: Int? = null
        set(value) {
            setUserThumbnail(value)
            field = value
        }

    private fun setUserThumbnail(imgId: Int?) {
        Glide.with(binding.mainUserThumbNail.context)
            .load(UrlContract.profileIconUrl(imgId ?: 0))
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

    var userMatches: Int = 0
        set(value) {
            binding.userMatches.text = context.getString(R.string.user_view_whole_match, value)
            field = value
        }

    var userWin: Int = 0
        set(value) {
            binding.userWin.text = context.getString(R.string.user_view_win_count, value)
            field = value
        }

    var userLose: Int = 0
        set(value) {
            binding.userLose.text = context.getString(R.string.user_view_lose_count, value)
            field = value
        }

    var userWinRate: Int = 0
        set(value) {
            binding.userWinScore.text = context.getString(R.string.user_view_win_rate, value)
            field = value
        }

    var userKda: Double = 0.0
        set(value) {
            binding.userKda.text = context.getString(R.string.user_view_kda, value)
            field = value
        }

    fun setClickListener(onClick: () -> Unit) {
        binding.userInfoDelete.setOnClickListener {
            onClick()
        }
    }

    fun setRefreshClickListener(onClick: () -> Unit) {
        binding.mainUserRefresh.setOnClickListener {
            onClick()
        }
    }
}