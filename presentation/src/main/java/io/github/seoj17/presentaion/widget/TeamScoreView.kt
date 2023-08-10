package io.github.seoj17.presentaion.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import io.github.seoj17.presentaion.databinding.ViewTeamScoreBinding
import io.github.seoj17.presentaion.utils.NumberFormatter

@BindingMethods(
    value = [
        BindingMethod(
            type = TeamScoreView::class,
            attribute = "winTeamScore",
            method = "setWinTeamScore",
        ),

        BindingMethod(
            type = TeamScoreView::class,
            attribute = "loseTeamScore",
            method = "setLoseTeamScore",
        ),
    ],
)
class TeamScoreView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private val binding =
        ViewTeamScoreBinding.inflate(
            LayoutInflater.from(context),
            this,
            true,
        )
    var winTeamScore: Int? = null
        set(value) {
            value?.let {
                binding.winTeamValue.text = NumberFormatter.formatNumber(it)
            }
            field = value
        }

    var loseTeamScore: Int? = null
        set(value) {
            value?.let {
                binding.loseTeamValue.text = NumberFormatter.formatNumber(it)
            }
            field = value
        }
}
