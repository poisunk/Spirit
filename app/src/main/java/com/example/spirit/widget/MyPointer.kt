package com.example.spirit.widget

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Scroller
import com.example.spirit.R

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

    private var mCount = 3

    private var selectedColor = Color.BLACK

    private val mScroller:Scroller = Scroller(context)


    init {
        if(attrs != null){
            val typedArray: TypedArray = context.obtainStyledAttributes(attrs, R.styleable.MyPointer)
            mCount = typedArray.getInt(R.styleable.MyPointer_count, mCount)
            selectedColor = typedArray.getColor(R.styleable.MyPointer_selectedColor, selectedColor)
        }
        mPaint.style = Paint.Style.FILL_AND_STROKE
    }

    private var midX = 0f
    private var midY = 0f
    private var radius = 0f
    private var circlesWidth = 0f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        midX = x + width/2
        midY = y + height/2
        radius = (height/2).toFloat()
        circlesWidth = radius * 2.5f

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val curMidX = midX + scrollX
        for(i:Int in 0 until mCount){
            val ItemX = midX + i * 2.5f * radius
            if(ItemX in (curMidX - measuredWidth/2)..(curMidX + measuredWidth/2)) {
                val percentage = Math.abs(curMidX - ItemX) / (measuredWidth / 2)
                mPaint.color = selectedColor
                mPaint.alpha =
                    (0xff * (1.0f - percentage)).toInt()
                val curRadius = radius * (1.0f - percentage * 0.5f)
//                Log.d(TAG, "alpha: " + mPaint.alpha)
                canvas.drawCircle(ItemX, midY, curRadius, mPaint)
            }
        }
    }

    private var mLastX = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.action){
            MotionEvent.ACTION_DOWN -> {
                Log.d(TAG, "Touch:DOWN")
                mLastX = event.x
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d(TAG,"Touch:MOVE")
                val dX = mLastX - event.x

                if(scrollX + dX in 0.0f..circlesWidth * (mCount - 1)) {
                    scrollBy(dX.toInt(), 0)
                }else if(scrollX + dX < 0){
                    scrollBy(-scrollX, 0)
                }else{
                    scrollBy((circlesWidth * (mCount - 1)).toInt() - scrollX, 0)
                }
                mLastX = event.x
            }
            MotionEvent.ACTION_UP -> {
                val curProportion = scrollX % circlesWidth
                Log.d(TAG, "curProportion: $curProportion")
                val curIndex = (scrollX / circlesWidth).toInt() + if(curProportion > circlesWidth/2) { 1 }else { 0 }
                smoothScrollToCircle(curIndex)
            }
        }
        return super.onTouchEvent(event)
    }

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

}