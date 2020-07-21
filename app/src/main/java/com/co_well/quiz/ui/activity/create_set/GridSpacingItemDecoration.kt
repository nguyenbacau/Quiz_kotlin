package com.co_well.quiz.ui.activity.create_set

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(spacing: Int, spanCount: Int) : RecyclerView.ItemDecoration() {
    val spacing = spacing
    val spanCout = spanCount

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = spacing
        outRect.bottom = spacing
        outRect.right = spacing

        if (parent.getChildAdapterPosition(view) < spanCout){
            outRect.left = spacing
        } else outRect.left = 0

    }
}