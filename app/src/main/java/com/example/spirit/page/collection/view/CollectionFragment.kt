package com.example.spirit.page.collection.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spirit.R
import com.example.spirit.page.collection.adapter.CollectionRecyclerAdapter
import com.example.spirit.page.collection.model.ItemCallBack
import kotlinx.android.synthetic.main.fragment_collection.*
import kotlinx.android.synthetic.main.fragment_toning.*

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class CollectionFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_collection,container,false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init(){
        val list = ArrayList<Int>()
        for(i:Int in 0..10){
            list.add(i)
        }
        val adapter = CollectionRecyclerAdapter(list)

        val itemCallBack= ItemCallBack(adapter)
        val itemTouchHelper= ItemTouchHelper(itemCallBack)
        itemTouchHelper.attachToRecyclerView(fragment_collection_recycler)

        fragment_collection_recycler.adapter = adapter
        fragment_collection_recycler.layoutManager = LinearLayoutManager(requireContext())


        fragment_collection_bar_back_button.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

}