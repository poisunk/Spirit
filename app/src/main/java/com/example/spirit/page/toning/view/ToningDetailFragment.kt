package com.example.spirit.page.toning.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.spirit.R
import kotlinx.android.synthetic.main.fragment_toning_details.*

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class ToningDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_toning_details,container,false)
    }

    fun init(){


        fragment_toning_detail_bar_back_button.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }
}