package com.heking.artandroid.CustomerView

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.heking.artandroid.R

class CircleView : View {
    internal var mColor = Color.RED
    internal var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    var center_raduis: Float = 0.0F
    var center_color: Int = Color.WHITE

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CircleView)
        mColor = attributes.getColor(R.styleable.CircleView_circle_color, Color.RED)
        center_raduis = attributes.getFloat(R.styleable.CircleView_center_raduis, 0.0F)
        center_color = attributes.getColor(R.styleable.CircleView_center_color, Color.WHITE)
        attributes.recycle()
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        val attributes = context.obtainStyledAttributes(attrs, R.styleable.CircleView)
        mColor = attributes.getColor(R.styleable.CircleView_circle_color, Color.RED)
        center_raduis = attributes.getFloat(R.styleable.CircleView_center_raduis, 0.0F)
        center_color = attributes.getColor(R.styleable.CircleView_center_color, Color.WHITE)
        attributes.recycle()
        init()
    }

    private fun init() {
        mPaint.color = mColor
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val contentWidth = width - paddingRight - paddingLeft
        val contentHeight = height - paddingTop - paddingBottom
        val raduis = Math.min(contentWidth, contentHeight) / 2
        val centerX = (contentWidth / 2.0).toFloat() + paddingLeft
        val centerY = (contentHeight / 2.0).toFloat() + paddingTop
        canvas!!.drawCircle(centerX, centerY, raduis.toFloat(), mPaint)


        var text_paint = Paint(Paint.ANTI_ALIAS_FLAG)
        text_paint.color = Color.WHITE
        text_paint.textSize = 30F
        text_paint.textAlign = Paint.Align.CENTER

        val fontMetrics = text_paint.fontMetrics
        fontMetrics.top
        fontMetrics.bottom
        canvas!!.drawText("半径:$raduis", centerX, centerY - fontMetrics.bottom / 2 - fontMetrics.top / 2, text_paint)

//        if (center_raduis != 0F) {
//            canvas!!.drawCircle(centerX + 50, centerY, center_raduis, Paint(center_color))
//        }
    }
}
