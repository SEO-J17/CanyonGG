package io.github.seoj17.canyongg.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import com.bumptech.glide.Glide
import io.github.seoj17.canyongg.databinding.MostChampCustomBinding
import okhttp3.internal.format

@BindingMethods(
    value = [
        BindingMethod(
            type = MostChampView::class,
            attribute = "champThumbnail",
            method = "setChampThumbnail"
        ),
        BindingMethod(
            type = MostChampView::class,
            attribute = "champWinRate",
            method = "setChampWinRate"
        ),
        BindingMethod(
            type = MostChampView::class,
            attribute = "champKda",
            method = "setChampKda"
        )
    ]
)

class MostChampView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private val binding =
        MostChampCustomBinding.inflate(LayoutInflater.from(context), this, true)

    fun setChampThumbnail(champ: String?) {
        val imgUrl = "https://ddragon.leagueoflegends.com/cdn/13.1.1/img/champion/"
        champ?.let {
            Glide.with(binding.champ.context)
                .load("${imgUrl}${champ}.png").into(binding.champ)
        }
    }

    fun setChampWinRate(winRate: Int?) {
        winRate?.let {
            binding.champWinScore.text = "${winRate}%"
        }
    }

    fun setChampKda(kda: Double?) {
        kda?.let {
            binding.champKda.text = "${format("%.2f", kda)} : 1"
        }
    }
}