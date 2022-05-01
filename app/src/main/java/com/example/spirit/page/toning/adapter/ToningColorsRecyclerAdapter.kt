package com.example.spirit.page.toning.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.spirit.R
import com.example.spirit.base.OnItemClickListener
import com.example.spirit.bean.ColorListBean

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class ToningColorsRecyclerAdapter(private val list:List<ColorListBean.Color>) : RecyclerView.Adapter<ToningColorsRecyclerAdapter.ViewHolder>() {


    private var onItemClickListener:OnItemClickListener? = null


    class ViewHolder(v:View,
                     private val onItemClickListener: OnItemClickListener?)
        : RecyclerView.ViewHolder(v), View.OnClickListener{

        val backgroundCard:CardView = v.findViewById(R.id.item_toning_recycler_color_card)
        val colorName:TextView = v.findViewById(R.id.item_toning_recycler_color_name)
        val colorMes:TextView = v.findViewById(R.id.item_toning_recycler_color_mes)

        init {
            backgroundCard.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            onItemClickListener?.onItemClick(v,position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_toning_recycler,parent,false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.colorName.text = list[position].name
        holder.colorMes.text = list[position].mes
        holder.backgroundCard.setCardBackgroundColor(Color.parseColor("#" + list[position].hex))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        this.onItemClickListener = onItemClickListener
    }
}