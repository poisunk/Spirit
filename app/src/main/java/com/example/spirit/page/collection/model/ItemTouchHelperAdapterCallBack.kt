package com.example.spirit.page.collection.model

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
interface ItemTouchHelperAdapterCallBack {

    fun onItemMove(fromPosition:Int,targetPosition: Int):Boolean
    fun onItemSwiped(position:Int)

}