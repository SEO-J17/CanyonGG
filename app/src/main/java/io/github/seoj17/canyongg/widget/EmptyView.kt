package io.github.seoj17.canyongg.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.StringRes
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import io.github.seoj17.canyongg.databinding.TabCustomBinding
import io.github.seoj17.canyongg.utils.OnButtonClickListener

@BindingMethods(
    value = [
        BindingMethod(
            type = EmptyView::class,
            attribute = "emptyTitle",
            method = "setTitle"
        ),
        BindingMethod(
            type = EmptyView::class,
            attribute = "emptyContent",
            method = "setContent"
        ),
        BindingMethod(
            type = EmptyView::class,
            attribute = "buttonName",
            method = "setButtonName"
        )
    ]
)

class EmptyView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : LinearLayout(context, attributeSet, defStyleAttr) {
    private val binding = TabCustomBinding.inflate(LayoutInflater.from(context), this, true)
    private lateinit var clickListener: OnButtonClickListener

    var title: CharSequence
        get() = binding.infoTabTitle.text
        set(value) {
            binding.infoTabTitle.text = value
        }

    fun setTitle(@StringRes id: Int) {
        binding.infoTabTitle.setText(id)
    }

    var content: CharSequence
        get() = binding.infoTabContent.text
        set(value) {
            binding.infoTabContent.text = value
        }

    fun setContent(@StringRes id: Int) {
        binding.infoTabContent.setText(id)
    }

    var button: String
        get() = binding.mainSearch.text.toString()
        set(value) {
            binding.mainSearch.text = value
        }

    fun setButtonName(name: String) {
        binding.mainSearch.text = name
    }

    fun setClickListener(clickListener: OnButtonClickListener) {
        binding.mainSearch.setOnClickListener {
            clickListener.onButtonClick()
        }
        this.clickListener = clickListener
    }
}