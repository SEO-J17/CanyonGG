package io.github.seoj17.canyongg.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import io.github.seoj17.canyongg.databinding.ViewMyRankBinding
import io.github.seoj17.canyongg.ui.model.MyRank

@BindingMethods(
    value = [
        BindingMethod(
            type = MyRankView::class,
            attribute = "rankTitle",
            method = "setTitle"
        ),
        BindingMethod(
            type = MyRankView::class,
            attribute = "rankInfo",
            method = "setRankInfo"
        ),
    ]
)

class MyRankView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private val binding = ViewMyRankBinding.inflate(LayoutInflater.from(context), this, true)

    var title: CharSequence? = null
        set(value) {
            binding.rankLabel.text = value
            field = value
        }

    var rankInfo: MyRank? = null
        set(value) {
            value?.let {
                graphMaxValue = it.maxValue
                myGraphValue = it.myValue
                myRankLabel = it.rank.toString()
                myValueLabel = it.myValue.toString()
                maxValueLabel = it.maxValue.toString()
            }
            field = value
        }

    var myValueLabel: CharSequence? = null
        set(value) {
            binding.myValue.text = "${value} / "
            field = value
        }

    var maxValueLabel: CharSequence? = null
        set(value) {
            binding.maxValue.text = value
            field = value
        }

    var myRankLabel: CharSequence? = null
        set(value) {
            binding.myRankValue.text = "${value}위"
            field = value
        }

    var myGraphValue: Int? = null
        set(value) {
            binding.valueGraph.progress = value ?: 0
            field = value
        }

    var graphMaxValue: Int? = null
        set(value) {
            binding.valueGraph.max = value ?: 0
            field = value
        }
}