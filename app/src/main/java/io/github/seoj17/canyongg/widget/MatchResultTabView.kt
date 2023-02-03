package io.github.seoj17.canyongg.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingMethod
import androidx.databinding.BindingMethods
import io.github.seoj17.canyongg.databinding.MatchResultTabCustomBinding

@BindingMethods(
    value = [
        BindingMethod(
            type = MatchResultTabView::class,
            attribute = "winTitle",
            method = "setWinTitle"
        ),
        BindingMethod(
            type = MatchResultTabView::class,
            attribute = "kill",
            method = "setKill"
        ),
        BindingMethod(
            type = MatchResultTabView::class,
            attribute = "death",
            method = "setDeath"
        ),
        BindingMethod(
            type = MatchResultTabView::class,
            attribute = "assist",
            method = "setAssist"
        ),
        BindingMethod(
            type = MatchResultTabView::class,
            attribute = "playTime",
            method = "setPlayTime"
        )
    ]
)

class MatchResultTabView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attributeSet, defStyleAttr) {
    private val binding =
        MatchResultTabCustomBinding.inflate(LayoutInflater.from(context), this, true)

}