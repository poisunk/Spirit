package com.example.spirit.page.toning.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spirit.R
import com.example.spirit.bean.ColorDetailBean
import kotlinx.android.synthetic.main.fragment_toning_share.*

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class ToningShareFragment(private val shade:List<ColorDetailBean.Data.Shade>)
    : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toning_share, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        fragment_toning_share_back_button.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }

        fragment_toning_share_collection.setOnClickListener {

        }

        val colorList = IntArray(shade.size)
        for(i:Int in shade.indices){
            colorList[i] = Color.parseColor("#"+shade[i].color.hex)
        }
        fragment_toning_share_shade_background.setShadeColors(colorList)
    }

}