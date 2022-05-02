package com.example.spirit.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class MyShadeCircleView(context: Context,
                        attrs: AttributeSet? = null,
                        defStyleAttr:Int = 0,
                        defStyleRes:Int = 0)
    : View(context, attrs, defStyleAttr, defStyleRes){

    constructor(context: Context, attrs: AttributeSet?):this(context, attrs, 0)

    private val mPaint = Paint()

    private var defaultColors = intArrayOf(Color.WHITE or (0xFF shl 24), Color.WHITE or (0xFF shl 24))

    private var defaultPositions = floatArrayOf(0.0f, 1.0f)

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint.shader = LinearGradient(measuredWidth.toFloat()/2, 0f, measuredWidth.toFloat()/2, measuredHeight.toFloat(),
            defaultColors , defaultPositions as FloatArray, Shader.TileMode.CLAMP)

        canvas.drawCircle(measuredWidth.toFloat()/2, measuredHeight.toFloat()/2, measuredHeight.toFloat()/2, mPaint)
    }

    fun setShadeColors(colorList:IntArray){
        if(colorList.size < 2){
            return
        }
        defaultColors = colorList
        val curPosition:FloatArray = FloatArray(colorList.size)
        for(i:Int in colorList.indices){
            curPosition[i] = i.toFloat() / (colorList.size - 1)
        }
        defaultPositions = curPosition
        invalidate()
    }

}