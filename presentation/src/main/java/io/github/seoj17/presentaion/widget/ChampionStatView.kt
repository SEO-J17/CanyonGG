package io.github.seoj17.presentaion.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import io.github.seoj17.presentaion.databinding.ViewChampStatBinding

@BindingMethods(
    value = [
        BindingMethod(
            type = ChampionStatView::class,
            attribute = "statLabel",
            method = "setStatLabel",
        ),
        BindingMethod(
            type = ChampionStatView::class,
            attribute = "stat",
            method = "setStat",
        ),
    ],
)
class ChampionStatView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private val binding = ViewChampStatBinding.inflate(LayoutInflater.from(context), this, true)

    var statLabel: CharSequence? = null
        set(value) {
            binding.champStatLabel.text = value
            field = value
        }

    var stat: CharSequence? = null
        set(value) {
            binding.champStat.text = value
            field = value
        }
}
