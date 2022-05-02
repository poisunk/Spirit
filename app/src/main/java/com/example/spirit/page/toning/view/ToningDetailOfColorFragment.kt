package com.example.spirit.page.toning.view

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
import com.example.spirit.page.toning.model.ToningDataUtil
import com.example.spirit.page.toning.viewmodel.ToningDetailViewModel
import com.example.spirit.widget.MyShadeView
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

        initMatchColors()
    }

    /**
     * 初始化渐变示例
     * 六个渐变示例逐个初始化
     */
    private fun initShadeExample(){
        val shade_list:List<ColorDetailBean.Data.ShadeList>? = viewModel.colorDetailBean?.data?.shades?.shade_list
        if(shade_list != null) {
            for (i: Int in shade_list.indices) {
                val view = fragment_toning_detail_shade_examples.getChildAt(i) as MyShadeView
                val colorList = IntArray(shade_list[i].shade.size)
                val shade = shade_list[i].shade
                for(i:Int in colorList.indices){
                    colorList[i] = Color.parseColor("#"+shade[i].color.hex)
                }
                view.setShadeColors(colorList)
                view.setOnClickListener {
                    val ft = requireActivity().supportFragmentManager.beginTransaction()
                    ft.add(R.id.activity_main, ToningShareFragment(shade_list[i].shade), "ToningShareFragment")
                        .addToBackStack("ToningShareFragment")
                        .commit()
                }
            }
        }

    }

    /**
     * 初始化配色示例
     */
    private fun initMatchColors(){
        val colors = viewModel.colorDetailBean?.data?.colors
        if(colors != null) {
            val hex_1 = "#" + colors.color_1.hex
            val hex_2 = "#" + colors.color_2.hex
            val hex_3 = "#" + colors.color_3.hex
            val hex_4 = "#" + colors.color_4.hex
            val hex_5 = "#" + colors.color_5.hex
            val hex_6 = "#" + colors.color_6.hex
            fragment_toning_detail_matches_1.setBackgroundColor(Color.parseColor(hex_1))
            fragment_toning_detail_matches_2.setBackgroundColor(Color.parseColor(hex_2))
            fragment_toning_detail_matches_3.setBackgroundColor(Color.parseColor(hex_3))
            fragment_toning_detail_matches_4.setBackgroundColor(Color.parseColor(hex_4))
            fragment_toning_detail_matches_5.setBackgroundColor(Color.parseColor(hex_5))
            fragment_toning_detail_matches_6.setBackgroundColor(Color.parseColor(hex_6))
            fragment_toning_detail_matches_1_hex.text = hex_1
            fragment_toning_detail_matches_2_hex.text = hex_2
            fragment_toning_detail_matches_3_hex.text = hex_3
            fragment_toning_detail_matches_4_hex.text = hex_4
            fragment_toning_detail_matches_5_hex.text = hex_5
            fragment_toning_detail_matches_6_hex.text = hex_6
        }
    }
}