package com.heking.artandroid.CustomerView

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CircleView : View {
    internal var mColor = Color.RED
    internal var mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        mPaint.color = mColor
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val raduis = Math.min(width, height)
        canvas!!.drawCircle((width / 2.0).toFloat(), (height / 2.0).toFloat(), raduis.toFloat(), mPaint)
    }
}
