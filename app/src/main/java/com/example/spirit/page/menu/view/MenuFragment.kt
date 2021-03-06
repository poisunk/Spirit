package com.example.spirit.page.menu.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spirit.R
import com.example.spirit.page.menu.adapter.OnItemClickListener
import com.example.spirit.page.collection.view.CollectionFragment
import com.example.spirit.page.menu.adapter.MenuRecyclerAdapter
import com.example.spirit.page.toning.view.ToningFragment
import kotlinx.android.synthetic.main.fragment_menu.*

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
        return inflater.inflate(R.layout.fragment_menu,container,false)
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init(){
        initRecycler()
    }

    private fun initRecycler(){
        val adapter = MenuRecyclerAdapter(3,this)
        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                when(position){
                    MenuRecyclerAdapter.TONING -> {
                        switchFragment(ToningFragment(), "ToningFragment")
                    }
                    MenuRecyclerAdapter.COLLECTION -> {
                        switchFragment(CollectionFragment(), "CollectionFragment")
                    }
                    else ->{
                        Toast.makeText(requireContext(),"此功能还未完成",Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
        main_menu_recycler_view.adapter = adapter
        main_menu_recycler_view.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun switchFragment(fragment:Fragment, tag:String){
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_exit, R.anim.fragment_pop_enter, R.anim.fragment_pop_exit)
            .add(R.id.activity_main, fragment, tag)
            .addToBackStack(tag)
            .hide(this)
            .commit()
    }
}
