package io.github.seoj17.canyongg.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import io.github.seoj17.canyongg.R
import io.github.seoj17.canyongg.databinding.ViewMostChampBinding
import io.github.seoj17.canyongg.ui.model.ChampInfo
import io.github.seoj17.canyongg.utils.setChampion

@BindingMethods(
    value = [
        BindingMethod(
            type = MostChampView::class,
            attribute = "champInfo",
            method = "setChampInfo"
        ),
    ]
)

class MostChampView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private val binding =
        ViewMostChampBinding.inflate(LayoutInflater.from(context), this, true)

    var champInfo: ChampInfo? = null
        set(value) {
            value?.let {
                champName = it.name
                champWinRate = it.winRate
                champKda = it.kda
            }
            field = value
        }

    var champName: CharSequence? = null
        set(value) {
            setChampThumbnail(value)
            field = value
        }

    private fun setChampThumbnail(champ: CharSequence?) {
        champ?.let {
            binding.champ.setChampion(champ.toString())
        }
    }

    var champWinRate: Int = 0
        set(value) {
            binding.champWinScore.text = resources.getString(R.string.empty_view_win_rate, value)
            field = value
        }

    var champKda: Double = 0.0
        set(value) {
            binding.champKda.text = resources.getString(R.string.empty_view_kda, value)
            field = value
        }
}