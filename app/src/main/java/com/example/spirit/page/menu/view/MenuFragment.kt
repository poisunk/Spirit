package com.example.spirit.page.menu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spirit.R
import com.example.spirit.page.menu.adapter.MenuRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_toning.*

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class MenuFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toning,container,false)
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init(){
        val list = listOf<Int>(1)
        main_menu_recycler_view.adapter = MenuRecyclerAdapter(1,this)
        main_menu_recycler_view.layoutManager = LinearLayoutManager(requireContext())
    }
}
