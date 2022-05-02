package com.example.spirit.page.toning.view

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.spirit.R
import com.example.spirit.bean.ColorDetailBean
import com.example.spirit.bean.ColorListBean
import com.example.spirit.page.toning.adapter.ToningColorsRecyclerAdapter
import com.example.spirit.page.toning.model.ToningDataUtil
import com.example.spirit.page.toning.viewmodel.ToningDetailViewModel
import com.example.spirit.widget.MyShadeCircleView
import kotlinx.android.synthetic.main.fragment_toning_detail_viewpager.*

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class ToningDetailOfColorFragment(private val color:ColorListBean.Color) : Fragment(){

    private val viewModel by lazy { ViewModelProviders.of(this).get(ToningDetailViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toning_detail_viewpager,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    override fun onStart() {
        super.onStart()
        viewModel.getColorDetail(color.id)
    }

    private fun init(){

        viewModel.colorDetailLiveData.observe(viewLifecycleOwner, Observer {
            val colorDetailBean = it.getOrNull()
            if(colorDetailBean?.data != null){
                viewModel.colorDetailBean = colorDetailBean
                initColorDetail()
            }else{
                it.exceptionOrNull()?.printStackTrace()
            }
        })

    }

    /**
     * 初始化颜色详情
     */
    private fun initColorDetail(){

        //设置第一项主要颜色的名字与卡片信息
        fragment_toning_detail_color_name.text = color.name
        fragment_toning_detail_color_card.setCardBackgroundColor(Color.parseColor("#" + color.hex))
        fragment_toning_detail_color_card_mes.text = ToningDataUtil.getColorMessage(color)

        initShadeExample()
    }

    /**
     * 初始化渐变示例
     * 六个渐变示例逐个初始化
     */
    private fun initShadeExample(){
        var shade_list:List<ColorDetailBean.Data.ShadeList>? = viewModel.colorDetailBean?.data?.shades?.shade_list
        if(shade_list != null) {
            for (i: Int in 1..6) {
                val view = fragment_toning_detail_shade_examples.getChildAt(i-1) as MyShadeCircleView
                val colorList = IntArray(shade_list[i].shade.size)
                val shade = shade_list[i].shade
                for(i:Int in colorList.indices){
                    colorList[i] = Color.parseColor("#"+shade[i].color.hex)
                }
                view.setShadeColors(colorList)
            }
        }

    }
}