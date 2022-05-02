package com.example.spirit.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.Scroller
import com.example.spirit.R
import kotlin.math.abs

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
@SuppressLint("Recycle")
class MyPointer(context:Context,
                attrs:AttributeSet? = null,
                defStyleAttr:Int = 0,
                defStyleRes:Int = 0)
    : View(context, attrs, defStyleAttr, defStyleRes){

    constructor(context: Context, attrs: AttributeSet?):this(context, attrs, 0)

    private val TAG:String = "MyPointer"

    private val mPaint: Paint = Paint()

    private var mCount = 3 //圆点的数量

    private var selectedColor = Color.BLACK //圆点的颜色

    private val mScroller:Scroller = Scroller(context)

    private var circleDistance = 0.5f //圆点间的间隔距离，如果为0则圆与圆之间间隔为0，如果为1则圆与圆之间间隔一个半径

    //初始化属性
    init {
        if(attrs != null){
            val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MyPointer)
            mCount = typedArray.getInt(R.styleable.MyPointer_count, mCount)
            selectedColor = typedArray.getColor(R.styleable.MyPointer_selectedColor, selectedColor)
            circleDistance = typedArray.getFloat(R.styleable.MyPointer_circleDistance,circleDistance)
        }
        mPaint.style = Paint.Style.FILL_AND_STROKE
    }

    private var midX = 0f //第一个圆的坐标
    private var midY = 0f
    private var radius = 0f //圆的半径
    private var circlesWidth = 0f //圆与圆之间的距离

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        midX = x + width/2
        midY = y + height/2
        radius = (height/2).toFloat()
        circlesWidth = radius * 2.0f + circleDistance * radius

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //当前画面中心点的坐标
        //用于计算圆的大小和渐变
        val curMidX = midX + scrollX
        for(i:Int in 0 until mCount){

            //第i个圆的x坐标
            val ItemX = midX + i * circlesWidth

            if(ItemX in (curMidX - measuredWidth/2)..(curMidX + measuredWidth/2)) {

                //计算圆到当前中心点的距离百分比，当到达画面边缘时为1，中心点为0
                val percentage = abs(curMidX - ItemX) / (measuredWidth / 2)

                mPaint.color = selectedColor
                mPaint.alpha =
                    (0xff * (1.0f - percentage)).toInt()
                val curRadius = radius * (1.0f - percentage * 0.5f)
//                Log.d(TAG, "alpha: " + mPaint.alpha)
                canvas.drawCircle(ItemX, midY * 2 - curRadius , curRadius, mPaint)
            }
        }
    }

    private val touchSlop = ViewConfiguration.get(context).scaledTouchSlop //最小滑动距离

    private var mLastX = 0f
    private var movedX = 0f //每次滑动事件滑动的距离

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
//                Log.d(TAG, "Touch:DOWN")
                mLastX = event.x
                return true
            }
            MotionEvent.ACTION_MOVE -> {
//                Log.d(TAG,"Touch:MOVE")
                val dX = mLastX - event.x
                //记录滑动距离
                movedX += dX

                //限制滑动范围
                when {
                    scrollX + dX in 0.0f..circlesWidth * (mCount - 1) -> {
                        scrollBy(dX.toInt(), 0)
                    }
                    scrollX + dX < 0 -> {
                        scrollBy(-scrollX, 0)
                    }
                    else -> {
                        scrollBy((circlesWidth * (mCount - 1)).toInt() - scrollX, 0)
                    }
                }

                mLastX = event.x
            }
            MotionEvent.ACTION_UP -> {
                //判断这次事件是点击还是滑动
                val eventEndX:Int = if (abs(movedX) > touchSlop) {
                    scrollX
                }else{
                    if (isInRange(event.x.toInt() - measuredWidth/2 + scrollX)){
                        event.x.toInt() - measuredWidth/2 + scrollX
                    }else{
                        scrollX
                    }
                }

                Log.d(TAG, "event.x = " + event.x + ",measureWidth = " + measuredWidth)

                val curIndex =
                    (eventEndX / circlesWidth).toInt() + if ( (eventEndX % circlesWidth) > circlesWidth / 2) {
                        1
                    } else {
                        0
                    }
                smoothScrollToCircle(curIndex)
                movedX = 0f
            }
        }
        return super.onTouchEvent(event)
    }

    /**
     * 光滑的滑动到index位置
     */
    fun smoothScrollToCircle(index:Int){
        val curX = index * circlesWidth
        mScroller.startScroll(scrollX, 0, (curX - scrollX).toInt(), 0, 500)
        invalidate()
    }

    override fun computeScroll(){
        super.computeScroll()
        if(mScroller.computeScrollOffset()){
            scrollTo(mScroller.currX,mScroller.currY)
            invalidate()
        }
    }

    private fun isInRange(x:Number):Boolean{
        return x.toFloat() in 0.0f..circlesWidth * (mCount - 1)
    }

    /**
     * 设置圆的数量
     */
    fun setCount(count:Int){
        mCount = count
        invalidate()
    }

}