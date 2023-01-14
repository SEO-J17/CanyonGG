package io.github.seoj17.canyongg

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import io.github.seoj17.canyongg.databinding.TabCustomBinding

class EmptyView(
    context: Context,
    attributeSet: AttributeSet,
) : LinearLayout(context, attributeSet) {
    private val binding = TabCustomBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        with(binding) {
            val infoTitle = infoTabTitle
            val infoContent = infoTabContent
            val applyBtn = mainSearch

            context
                .theme
                .obtainStyledAttributes(
                    attributeSet,
                    R.styleable.EmptyView,
                    0,
                    0
                ).apply {
                    try {
                        infoTitle.text = getString(R.styleable.EmptyView_emptyTitle)
                        infoContent.text = getString(R.styleable.EmptyView_emptyContent)
                        applyBtn.text = getString(R.styleable.EmptyView_buttonName)
                    } finally {
                        recycle()
                    }
                }
        }
    }
}