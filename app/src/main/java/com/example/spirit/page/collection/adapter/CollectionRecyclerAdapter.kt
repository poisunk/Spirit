package com.example.spirit.page.collection.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.spirit.R
import com.example.spirit.page.collection.model.ItemTouchHelperAdapterCallBack
import java.util.*
import kotlin.collections.ArrayList

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class CollectionRecyclerAdapter(val list:ArrayList<Int>):
    RecyclerView.Adapter<CollectionRecyclerAdapter.ViewHolder>() , ItemTouchHelperAdapterCallBack {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_collection_recycler,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){


    }

    override fun onItemMove(fromPosition: Int, targetPosition: Int): Boolean {
        Collections.swap(list,fromPosition,targetPosition)
        notifyItemMoved(fromPosition,targetPosition)
        return true
    }

    override fun onItemSwiped(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }


}