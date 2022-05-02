package com.example.spirit.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.spirit.R

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
@SuppressLint("Recycle")
class MyShadeView(context: Context,
                  attrs: AttributeSet? = null,
                  defStyleAttr:Int = 0,
                  defStyleRes:Int = 0)
    : View(context, attrs, defStyleAttr, defStyleRes){

    constructor(context: Context, attrs: AttributeSet?):this(context, attrs, 0)

    companion object{
        const val CIRCLE = 0
        const val RECT = 1
    }

    private val mPaint = Paint()

    private var mShape = CIRCLE

    private var defaultColors = intArrayOf(Color.WHITE or (0xFF shl 24), Color.WHITE or (0xFF shl 24))

    private var defaultPositions = floatArrayOf(0.0f, 1.0f)

    init {
        if(attrs != null){
            val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MyShadeView)
            mShape = typedArray.getInt(R.styleable.MyShadeView_shape, mShape)
        }

        mPaint.shader = LinearGradient(measuredWidth.toFloat()/2, 0f, measuredWidth.toFloat()/2, measuredHeight.toFloat(),
            defaultColors , defaultPositions, Shader.TileMode.CLAMP)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        when(mShape){
            CIRCLE -> {
                canvas.drawCircle(measuredWidth.toFloat()/2, measuredHeight.toFloat()/2, measuredHeight.toFloat()/2, mPaint)
            }
            RECT -> {
                canvas.drawRect(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat(), mPaint)
            }
        }
    }

    /**
     * 设置渐变颜色
     * @param colorList
     */
    fun setShadeColors(colorList:IntArray){
        val curPosition = FloatArray(colorList.size)
        for(i:Int in colorList.indices){
            curPosition[i] = i.toFloat() / (colorList.size - 1)
        }
        mPaint.shader = LinearGradient(measuredWidth.toFloat()/2, 0f, measuredWidth.toFloat()/2, measuredHeight.toFloat(),
            colorList , curPosition, Shader.TileMode.MIRROR)
        invalidate()
    }

}