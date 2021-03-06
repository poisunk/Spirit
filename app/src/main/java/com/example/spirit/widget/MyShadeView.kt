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

    private var mShape = CIRCLE //需要绘制的形状

    //默认的渐变颜色
    //设置成白色是因为有时候加载太慢，显示其他颜色会很奇怪
    private var defaultColors = intArrayOf(Color.WHITE , Color.WHITE)

    //默认的颜色分布
    private var defaultPositions = floatArrayOf(0.0f, 1.0f)

    private var mLinearGradient: LinearGradient? = null

    private var isChangedColor:Boolean = false

    init {
        if(attrs != null){
            val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MyShadeView)
            mShape = typedArray.getInt(R.styleable.MyShadeView_shape, mShape)
        }
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //判断线性渐变是否被初始化，或者有没有再去改变渐变的颜色
        if(mLinearGradient == null || isChangedColor){
            mLinearGradient = LinearGradient( measuredWidth.toFloat(), 0f,  measuredWidth.toFloat(), measuredHeight.toFloat(),
                defaultColors , defaultPositions, Shader.TileMode.CLAMP)
            isChangedColor = false
        }
        mPaint.shader = mLinearGradient


        //判断绘制圆形还是矩形
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
        val size = 1f / (colorList.size - 1)
        for(i:Int in colorList.indices){
            curPosition[i] = size * i
        }
        defaultColors = colorList
        defaultPositions = curPosition

        isChangedColor = true

        invalidate()
    }

}