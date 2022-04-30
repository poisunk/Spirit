package com.example.spirit.page.menu.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.spirit.R
import com.example.spirit.base.BaseActivity

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class MenuRecyclerAdapter(private val size:Int, private val fragment:Fragment) : RecyclerView.Adapter<MenuRecyclerAdapter.ViewHolder>() {

    val TONING = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(position){
            TONING -> {
                Glide.with(fragment).load(R.drawable.menu_toning).into(holder.icon)
                holder.text.text = "色谱"
            }
        }
    }

    override fun getItemCount(): Int {
        return size
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val icon:ImageView = v.findViewById<ImageView>(R.id.item_menu_image)
        val text:TextView = v.findViewById<TextView>(R.id.item_menu_text)
    }
}