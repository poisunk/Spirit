package com.example.spirit.page.toning.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spirit.R
import com.example.spirit.page.toning.adapter.ToningColorsRecyclerAdapter
import com.example.spirit.page.toning.viewmodel.ToningRecyclerViewModel
import kotlinx.android.synthetic.main.fragment_toning_recycler.*

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class ToningRecyclerFragment(private val colorPageId:Int) : Fragment() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(ToningRecyclerViewModel::class.java) }

    private val TAG:String = "ToningRecyclerFragment"

    private lateinit var adapter: ToningColorsRecyclerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toning_recycler,container,false)
    }

    override fun onStart() {
        super.onStart()
        init()
        viewModel.getColorList(colorPageId)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun init(){

        adapter = ToningColorsRecyclerAdapter(viewModel.colorList)

        fragment_toning_recycler_view.adapter = adapter
        fragment_toning_recycler_view.layoutManager = LinearLayoutManager(requireContext())

        viewModel.colorListLiveData.observe(this, Observer {
            val colorListBean = it.getOrNull()

            if(colorListBean?.data?.color_list != null){
                viewModel.colorList.clear()
                viewModel.colorList.addAll(colorListBean.data.color_list)
                adapter.notifyDataSetChanged()
            }else{
                it.exceptionOrNull()?.printStackTrace()
            }
        })
    }
}