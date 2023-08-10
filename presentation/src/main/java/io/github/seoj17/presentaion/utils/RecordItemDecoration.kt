package io.github.seoj17.presentaion.utils

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.seoj17.presentaion.R

class RecordItemDecoration(
    private val dividerHeight: Int = 20,
) : RecyclerView.ItemDecoration() {
    private val paint = Paint()

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val dividerColor = parent.context.getColor(R.color.divider_color)
        super.onDraw(c, parent, state)
        verticalDivider(c, parent, dividerColor)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = dividerHeight
    }

    private fun verticalDivider(c: Canvas, parent: RecyclerView, color: Int) {
        paint.color = color
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as? GridLayoutManager.LayoutParams ?: return

            val dividerTop = child.bottom - params.bottomMargin
            val dividerBottom = dividerTop + dividerHeight

            c.drawRect(
                child.left.toFloat(),
                dividerTop.toFloat(),
                child.right.toFloat(),
                dividerBottom.toFloat(),
                paint,
            )
        }
    }
}
