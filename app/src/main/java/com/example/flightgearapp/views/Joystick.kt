package com.example.flightgearapp.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.lang.Math.abs
import java.lang.Math.min

class Joystick @JvmOverloads constructor (
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    // paramater for drawing

    private val innerCyclePaint  = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color = Color.BLACK
        isAntiAlias = true
    }

    private val outerCyclePaint = Paint().apply {
        style = Paint.Style.FILL_AND_STROKE
        color =  Color.GRAY
        isAntiAlias = true

    }

    private var smallRadius: Float = 0F
    private var smallCenter: PointF = PointF()
    private var bigRadius: Float = 0f
    private var bigCenter: PointF = PointF()
    var aileron: Float = 0f
    var elevator: Float = 0f


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        smallRadius = 0.1f * min(w, h)
        smallCenter = PointF(width / 2.0f, height / 2.0f)
        bigRadius = 0.3f * min(w, h)
        bigCenter = PointF(width / 2.0f, height / 2.0f)
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawCircle(bigCenter.x, bigCenter.y, bigRadius, outerCyclePaint)
        canvas.drawCircle(smallCenter.x, smallCenter.y, smallRadius, innerCyclePaint)
        //canvas.drawCircle(500F, 500F, 50F, innerCyclePaint)
    }

    //  @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event == null) {
            return true
        }
        when (event.action) {
            MotionEvent.ACTION_UP -> release(event.x, event.y)
            MotionEvent.ACTION_MOVE -> touchMove(event.x, event.y)
        }
        return true
    }

    private fun release(x: Float, y: Float) {
        smallCenter.x = bigCenter.x
        smallCenter.y = bigCenter.y
        onChange(0.00F, 0.00F)
        invalidate()
    }

    private fun touchMove(x: Float, y: Float) {
        val distanceX = kotlin.math.abs(x - bigCenter.x)
        val distanceY = kotlin.math.abs(y - bigCenter.y)
        if ((bigRadius >= distanceX) && (bigRadius >= distanceY)) {
            smallCenter.x = x;
            smallCenter.y = y;
            aileron = (x - bigCenter.x) / (bigRadius)
            elevator = (y - bigCenter.y) / (bigRadius)
            //println("elevator :$aileron")
            //println("aileron :$elevator")
            onChange(aileron, elevator)
        }

        invalidate()
    }

    lateinit var onChange: (Float, Float) -> Unit

}