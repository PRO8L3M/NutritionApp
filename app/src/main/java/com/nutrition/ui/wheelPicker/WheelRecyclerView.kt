package com.nutrition.ui.wheelPicker

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class WheelRecyclerView constructor(context: Context, attrs: AttributeSet?) : RecyclerView(
    context,
    attrs
) {

    override fun isPaddingOffsetRequired(): Boolean {
        return !clipToPadding
    }

    override fun getLeftPaddingOffset(): Int {
        return if (clipToPadding) 0 else -paddingLeft
    }

    override fun getTopPaddingOffset(): Int {
        return if (clipToPadding) 0 else -paddingTop
    }

    override fun getRightPaddingOffset(): Int {
        return if (clipToPadding) 0 else paddingRight
    }

    override fun getBottomPaddingOffset(): Int {
        return if (clipToPadding) 0 else paddingBottom
    }
}