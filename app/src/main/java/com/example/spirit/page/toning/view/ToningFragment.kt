package com.example.spirit.page.toning.view

import android.annotation.SuppressLint
import android.graphics.Color
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
import kotlinx.android.synthetic.main.item_menu.*

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */

class ToningFragment : Fragment(){

    private val viewModel by lazy { ViewModelProviders.of(this).get(ToningViewModel::class.java) }

    private lateinit var adapter: ToningViewPaperAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toning,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getColorPage()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun init(){
        adapter = ToningViewPaperAdapter(requireActivity(),viewModel.fragments)
        fragment_toning_view_paper2.adapter = adapter

        viewModel.colorPageLiveData.observe(viewLifecycleOwner, Observer {
            val colorPageBean = it.getOrNull()
            if(colorPageBean?.data != null){
                initViewPaper(colorPageBean)
                viewModel.colorPages.clear()
                viewModel.colorPages.addAll(colorPageBean.data.list)
                adapter.notifyDataSetChanged()
            }else{
                it.exceptionOrNull()?.printStackTrace()
            }
        })

        fragment_toning_tool_bar_back_button.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        fragment_toning_view_paper2.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback() {
            var currentIndex = 0
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                currentIndex = position
            }
            override fun onPageScrollStateChanged(state: Int) {
                if(state == ViewPager2.SCROLL_STATE_IDLE){
                    fragment_toning_tool_bar_theme.text = viewModel.themeList[currentIndex]
                    fragment_toning_tool_bar_pointer.smoothScrollToCircle(currentIndex)
                }
            }
        })

        var mStatusBarHeight:Float = 0f
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId != 0) {
            mStatusBarHeight = resources.getDimensionPixelSize(resourceId).toFloat()
        }
        fragment_toning_frame.y = mStatusBarHeight
    }

    private fun initViewPaper(colorPageBean: ColorPageBean){
        viewModel.fragments.clear()
        viewModel.themeList.clear()
        for(list:ColorPageBean.list in colorPageBean.data.list){
            viewModel.fragments.add(ToningRecyclerFragment(list.id))
            viewModel.themeList.add(list.theme)
        }
        if(viewModel.themeList.size > 0)
        fragment_toning_tool_bar_theme.text = viewModel.themeList[0]
        fragment_toning_tool_bar_pointer.setCount(viewModel.themeList.size)
    }
}