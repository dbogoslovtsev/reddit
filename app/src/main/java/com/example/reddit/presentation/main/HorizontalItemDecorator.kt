package com.example.reddit.presentation.main


import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View

class HorizontalItemDecorator(private var spacing: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        outRect.set(0, 0, 0, spacing)
    }

}
