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
import com.example.spirit.bean.ColorListBean
import com.example.spirit.page.toning.adapter.ToningViewPaperAdapter
import com.example.spirit.page.toning.viewmodel.ToningDetailViewModel
import kotlinx.android.synthetic.main.fragment_toning.*
import kotlinx.android.synthetic.main.fragment_toning_details.*

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class ToningDetailFragment(private val colorList:List<ColorListBean.Color>)
    : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toning_details,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    fun init(){

        fragment_toning_detail_bar_back_button.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        var mStatusBarHeight:Float = 0f
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId != 0) {
            mStatusBarHeight = resources.getDimensionPixelSize(resourceId).toFloat()
        }
        fragment_toning_detail_frame.y = mStatusBarHeight

        initViewPager()
    }

    private fun initViewPager(){
        val fragments = ArrayList<Fragment>()
        for(color:ColorListBean.Color in colorList){
            fragments.add(ToningDetailOfColorFragment(color))
        }
        fragment_toning_detail_view_paper2.adapter = ToningViewPaperAdapter(requireActivity(),fragments)

        fragment_toning_detail_bar_name.text = colorList[0].name
        fragment_toning_detail_bar_pointer.setCount(colorList.size)
        fragment_toning_detail_view_paper2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            var currentPosition = 0
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                currentPosition = position
            }

            override fun onPageScrollStateChanged(state: Int) {
                if(state == ViewPager2.SCROLL_STATE_IDLE){
                    fragment_toning_detail_bar_name.text = colorList[currentPosition].name
                    fragment_toning_detail_bar_pointer.smoothScrollToCircle(currentPosition)
                }
            }
        })
    }
}