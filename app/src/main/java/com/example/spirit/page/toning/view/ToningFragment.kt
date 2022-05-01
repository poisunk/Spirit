package com.example.spirit.page.toning.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2
import com.example.spirit.R
import com.example.spirit.bean.ColorPageBean
import com.example.spirit.page.toning.adapter.ToningViewPaperAdapter
import com.example.spirit.page.toning.viewmodel.ToningViewModel
import kotlinx.android.synthetic.main.fragment_toning.*

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */

class ToningFragment : Fragment(){

    private val viewModel by lazy { ViewModelProviders.of(this).get(ToningViewModel::class.java) }

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

    override fun onResume() {
        super.onResume()
        viewModel.getColorPage()
    }

    private fun init(){

        viewModel.colorPageLiveData.observe(this, Observer {
            val colorPageBean = it.getOrNull()
            if(colorPageBean?.data != null){
                initViewPaper(colorPageBean)
                viewModel.colorPages.clear()
                viewModel.colorPages.addAll(colorPageBean.data.list)
            }else{
                it.exceptionOrNull()?.printStackTrace()
            }
        })
    }

    private fun initViewPaper(colorPageBean: ColorPageBean){
        viewModel.fragments.clear()
        for(list:ColorPageBean.list in colorPageBean.data.list){
            viewModel.fragments.add(ToningRecyclerFragment(list.id))
        }
        val adapter = ToningViewPaperAdapter(requireActivity(),viewModel.fragments)
        fragment_toning_view_paper2.adapter = adapter

        fragment_toning_tool_bar_theme.text = colorPageBean.message

//        fragment_toning_view_paper2.registerOnPageChangeCallback(object:
//            ViewPager2.OnPageChangeCallback() {
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
//            }
//        })
    }
}