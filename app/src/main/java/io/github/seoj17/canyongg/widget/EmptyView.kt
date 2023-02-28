package io.github.seoj17.canyongg.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import io.github.seoj17.canyongg.databinding.ViewEmptyBinding

@BindingMethods(
    value = [
        BindingMethod(
            type = EmptyView::class,
            attribute = "emptyTitle",
            method = "setTitle",
        ),
        BindingMethod(
            type = EmptyView::class,
            attribute = "emptyContent",
            method = "setContent",
        ),
        BindingMethod(
            type = EmptyView::class,
            attribute = "buttonName",
            method = "setButtonName",
        ),
    ]
)

class EmptyView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attributeSet, defStyleAttr) {
    private val binding = ViewEmptyBinding.inflate(LayoutInflater.from(context), this, true)

    var title: CharSequence? = null
        set(value) {
            binding.infoTabTitle.text = value
            field = value
        }

    var content: CharSequence? = null
        set(value) {
            binding.infoTabContent.text = value
            field = value
        }

    var buttonName: CharSequence? = null
        set(value) {
            binding.mainSearch.text = value
            field = value
        }

    fun setClickListener(onClick: () -> Unit) {
        binding.mainSearch.setOnClickListener {
            onClick()
        }
    }
}