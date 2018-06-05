package com.example.reddit.customview

import android.content.Context
import android.widget.ImageView
import android.util.AttributeSet
import android.view.View


class DynamicImageView(context: Context, attrs: AttributeSet) : ImageView(context, attrs) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val d = this.drawable

        if (d != null) {
            // ceil not round - avoid thin vertical gaps along the left/right edges
            val width = View.MeasureSpec.getSize(widthMeasureSpec)
            val height = Math.ceil((width * d.intrinsicHeight.toFloat() / d.intrinsicWidth).toDouble()).toInt()
            this.setMeasuredDimension(width, height)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }
    }
}