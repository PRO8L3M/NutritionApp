package com.nutrition.ui.horizontalRecycler

import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class DotsPagerIndicatorDecoration : RecyclerView.ItemDecoration() {

    private val colorActive = -0x1
    private val colorInactive = 0x66FFFFFF

    private val DP: Float = Resources.getSystem().displayMetrics.density

    private val radius = 7

    private var activePosition = 0

    /**
     * Height of the space the indicator takes up at the bottom of the view.
     */
    private val mIndicatorHeight = DP * 8

    /**
     * Indicator stroke width.
     */
    private val mIndicatorStrokeWidth = DP * 4

    /**
     * Padding between indicators.
     */
    private val mIndicatorItemPadding = DP * 2

    private val mPaint: Paint = Paint().apply {
        strokeCap = Paint.Cap.ROUND
        strokeWidth = mIndicatorStrokeWidth
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val itemCount = parent.adapter!!.itemCount

        // center horizontally, calculate width and subtract half from center
        val totalLength = radius * 2 * itemCount
        val paddingBetweenItems = 0.coerceAtLeast(itemCount - 1) * mIndicatorItemPadding
        val indicatorTotalWidth = totalLength + paddingBetweenItems
        val indicatorStartX = (parent.width - indicatorTotalWidth) / 2f

        // center vertically in the allotted space
        val indicatorPosY = parent.height - mIndicatorHeight / 2f
        drawInactiveIndicators(c, indicatorStartX, indicatorPosY, itemCount)


        // find active page (which should be highlighted)
        val layoutManager = parent.layoutManager as LinearLayoutManager?
        val completelyVisibleItemPosition = layoutManager!!.findFirstCompletelyVisibleItemPosition()
        if (completelyVisibleItemPosition != -1) {
            activePosition = completelyVisibleItemPosition
        }
        if (activePosition == RecyclerView.NO_POSITION) {
            return
        }

        // on swipe the active item will be positioned from [-width, 0]
        drawHighlights(c, indicatorStartX, indicatorPosY, activePosition)
    }

    private fun drawInactiveIndicators(
        c: Canvas,
        indicatorStartX: Float,
        indicatorPosY: Float,
        itemCount: Int
    ) {
        mPaint.color = colorInactive
        // width of item indicator including padding
        val itemWidth = radius * mIndicatorItemPadding
        var start = indicatorStartX
        for (i in 0 until itemCount) {
            // draw the line for every item
            c.drawCircle(start, indicatorPosY, radius.toFloat(), mPaint)
            start += itemWidth
        }
    }

    private fun drawHighlights(
        c: Canvas,
        indicatorStartX: Float,
        indicatorPosY: Float,
        highlightPosition: Int
    ) {
        mPaint.color = colorActive
        val itemWidth = radius * mIndicatorItemPadding
        val highlightStart = indicatorStartX + radius + itemWidth * highlightPosition
        c.drawCircle(highlightStart, indicatorPosY, radius.toFloat(), mPaint)

    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = mIndicatorHeight.toInt()
    }
}