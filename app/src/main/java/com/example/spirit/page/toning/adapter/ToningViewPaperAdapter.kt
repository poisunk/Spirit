package com.example.spirit.page.toning.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class ToningViewPaperAdapter(fragmentActivity: FragmentActivity,
                             private val fragments:ArrayList<Fragment>)
    : FragmentStateAdapter(fragmentActivity){



    override fun getItemCount(): Int {
        return fragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }


}