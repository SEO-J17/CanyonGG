package io.github.seoj17.presentaion.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import io.github.seoj17.presentaion.R
import io.github.seoj17.presentaion.databinding.ViewMyRankBinding
import io.github.seoj17.presentaion.model.MyRank
import io.github.seoj17.presentaion.utils.NumberFormatter

@BindingMethods(
    value = [
        BindingMethod(
            type = MyRankView::class,
            attribute = "rankTitle",
            method = "setTitle",
        ),
        BindingMethod(
            type = MyRankView::class,
            attribute = "rankInfo",
            method = "setRankInfo",
        ),
    ],
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
                myValueLabel = it.myValue
                maxValueLabel = it.maxValue
            }
            field = value
        }

    var myValueLabel: Int? = null
        set(value) {
            binding.myValue.text =
                context.getString(
                    R.string.rank_view_value,
                    value?.let { NumberFormatter.formatNumber(it) },
                )
            field = value
        }

    var maxValueLabel: Int? = null
        set(value) {
            binding.maxValue.text = context.getString(
                R.string.rank_max_value,
                value?.let { NumberFormatter.formatNumber(it) },
            )
            field = value
        }

    var myRankLabel: CharSequence? = null
        set(value) {
            val color = when (value) {
                "1" -> {
                    R.color.first_rank
                }

                "2" -> {
                    R.color.second_rank
                }

                "3" -> {
                    R.color.third_rank
                }

                else -> R.color.general_text_color
            }
            binding.myRankValue.text = context.getString(R.string.rank_view_ranking, value)
            binding.myRankValue.setTextColor(context.getColor(color))
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
            binding.valueGraph.progressDrawable = context.getDrawable(R.drawable.rank_progress_bar)
            field = value
        }
}
