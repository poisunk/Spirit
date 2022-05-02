package com.example.spirit.page.collection.model

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class ItemCallBack(private val callback:ItemTouchHelperAdapterCallBack) : ItemTouchHelper.Callback() {

    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlags:Int= ItemTouchHelper.UP or ItemTouchHelper.DOWN
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