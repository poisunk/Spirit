# Spirit
红岩期中考核

# App介绍

主要功能展示：

![ezgif-4-5e46842126.gif](https://github.com/poisunk/Spirit/blob/main/intro_image/ezgif-4-5e46842126.gif?raw=true)



# 技术亮点

​		这个App我认为写的还不错的地方，就是色谱界面用了自定义的MyPointer圆形指示器，自定义形状的渐变颜色展示View，还有收藏界面使用ItemTouchHelper为RecyclerView添加了长按拖动还有滑动删除的效果。然后框架方面使用了MVVM框架，网络请求使用了retrofit当前主流的网络请求库。

​		MyPointer里主要是重写了onDraw与onTouchEvent，实现了滑动时圆点随着与当前中心点的距离增大而逐渐淡出的效果。

~~~kotlin
override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    //当前画面中心点的坐标
    //用于计算圆的大小和渐变
    val curMidX = firstCircleX + scrollX
    mPaint.color = selectedColor

    //依次绘制每一个点
    for(i:Int in 0 until mCount){

        //第i个圆的x坐标
        val ItemX = firstCircleX + i * circleSeparateWidth

        //如果当前要绘制的圆不在显示范围内，就不去绘制
        if(ItemX in (curMidX - measuredWidth/2)..(curMidX + measuredWidth/2)) {

            //计算圆到当前中心点的距离百分比，当到达画面边缘时为1，中心点为0
            val percentage = abs(curMidX - ItemX) / (measuredWidth / 2)

            //根据百分比计算透明度与半径
            mPaint.alpha =
            (0xff * (1.0f - percentage)).toInt()
            val curRadius = radius * (1.0f - percentage * 0.5f)

            //绘制
            canvas.drawCircle(ItemX, firstCircleY * 2 - curRadius , curRadius, mPaint)
        }
    }
}
~~~

​		在onTouchEvent中会根据这次事件的移动距离判断当前事件试一次点击还是滑动，然后根据结束的位置，向最近的圆点移动，最终使圆点移动到中心位置，移动完成后会返回移动的位置给监听者们（这里的监听者是使用ArrayList来储存的）。

~~~kotlin
override fun onTouchEvent(event: MotionEvent): Boolean {
    when(event.action){
        MotionEvent.ACTION_DOWN -> {
            mLastX = event.x
        }
        MotionEvent.ACTION_MOVE -> {
            val dX = mLastX - event.x
            //记录滑动距离
            movedX += dX

            //限制滑动范围
            when {
                scrollX + dX in 0.0f..circleSeparateWidth * (mCount - 1) -> {
                    scrollBy(dX.toInt(), 0)
                }
                scrollX + dX < 0 -> {
                    scrollBy(-scrollX, 0)
                }
                else -> {
                    scrollBy((circleSeparateWidth * (mCount - 1)).toInt() - scrollX, 0)
                }
            }

            mLastX = event.x
        }
        MotionEvent.ACTION_UP -> {

            //判断这次事件是点击还是滑动
            val eventEndX:Int = if (abs(movedX) > touchSlop) {
                //如果是滑动
                scrollX
            }else{
                //如果是点击，判断点击是否在范围内
                if (isInRange(event.x.toInt() - measuredWidth/2 + scrollX)){
                    event.x.toInt() - measuredWidth/2 + scrollX
                }else{
                    scrollX
                }
            }

            //根据当前事件的结束位置，计算要滑动到第几个圆
            val curIndex =
            (eventEndX / circleSeparateWidth).toInt() + if ( (eventEndX % circleSeparateWidth) > circleSeparateWidth / 2) {
                1
            } else {
                0
            }
            smoothScrollToCircle(curIndex)
            movedX = 0f

            //对监听者们返回监听
            callBackPointerListener(curIndex)
        }
    }
    return true
}
~~~

​		用来展示渐变示例的View只是重写了onDraw方法。

~~~kotlin
override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    //判断线性渐变是否被初始化，或者有没有再去改变渐变的颜色
    if(mLinearGradient == null || isChangedColor){
        mLinearGradient = LinearGradient( measuredWidth.toFloat(), 0f,  measuredWidth.toFloat(), measuredHeight.toFloat(),defaultColors , defaultPositions, Shader.TileMode.CLAMP)
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
~~~

​		收藏界面的实现主要还是使用了官方给出的工具ItemTouchHelper，首先是要实现ItemTouchHelperAdapterCallBack接口，然后让你的RecyclerView的adapter实现它，我们就可以在ItemTouchHelper里调用了。

ItemTouchHelperAdapterCallBack：

~~~kotlin
interface ItemTouchHelperAdapterCallBack {
	//当Item长按滑动
    fun onItemMove(fromPosition:Int,targetPosition: Int):Boolean
    //当Item被滑动删除
    fun onItemSwiped(position:Int)
}
~~~

ItemTouchHelper：

~~~kotlin
class ItemCallBack(private val callback:ItemTouchHelperAdapterCallBack) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        //拖动
        val dragFlags:Int= ItemTouchHelper.UP or ItemTouchHelper.DOWN
        //滑动
        val swipeFlags:Int = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return makeMovementFlags(dragFlags,swipeFlags)
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return callback.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        callback.onItemSwiped(viewHolder.adapterPosition)
    }

}
~~~

RecyclerViewAdapter：

~~~kotlin
class MyRecyclerAdapter(val list:ArrayList<Int>):
    RecyclerView.Adapter<CollectionRecyclerAdapter.ViewHolder>() , ItemTouchHelperAdapterCallBack {

    /**
    * 交换位置
    */
    override fun onItemMove(fromPosition: Int, targetPosition: Int): Boolean {
        Collections.swap(list,fromPosition,targetPosition)
        notifyItemMoved(fromPosition,targetPosition)
        return true
    }
    
    /**
    * 滑动删除
    */
    override fun onItemSwiped(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
        
}
~~~

# 有待提升的地方

- fragment之间的切换做的不太好，太生硬了。
- 按键的点击效果也可以做一做。
- 没有使用数据存储。
- ItemTouchHelper还可以在深入了解一下，这次只实现了最基础的效果。
- 对StatusBar的适配做的不太好，这次我用的是协调者布局，然后填充StatusBar的时候是把CoordinatorLayout设置为StatusBar的高度，然后在behavior里，修改了子View的布局。
