package io.github.seoj17.canyongg

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import io.github.seoj17.canyongg.databinding.UserTabCustomBinding

class UserView(
    context: Context,
    attributeSet: AttributeSet,
) : ConstraintLayout(context, attributeSet) {
    private val binding = UserTabCustomBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        with(binding) {
            val thumbNail = mainUserThumbNail
            val userLevel = mainUserLevel
            val userName = mainUserName
            val userTier = mainUserTier
            val infoRefresh = mainUserRefresh
            val infoDelete = userInfoDelete

            context
                .theme
                .obtainStyledAttributes(
                    attributeSet,
                    R.styleable.UserView,
                    0,
                    0
                ).apply {
                    try {
                        thumbNail.setImageIcon(this, R.styleable.UserView_mainUserThumbnail)
                        userLevel.text = getString(R.styleable.UserView_mainUserLevel)
                        userName.text = getString(R.styleable.UserView_mainUserName)
                        userTier.text = getString(R.styleable.UserView_mainUserTier)
                        infoRefresh.setImageIcon(this, R.styleable.UserView_mainUserReload)
                        infoDelete.setImageIcon(this, R.styleable.UserView_mainUserDelete)
                    } finally {
                        recycle()
                    }
                }
        }
    }

    private fun ImageView.setImageIcon(typedArray: TypedArray, imgId: Int) {
        this.setImageResource(typedArray.getResourceId(imgId, 0))
    }
}