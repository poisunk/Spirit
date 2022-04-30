package com.example.spirit.page

import android.os.Bundle
import android.view.KeyEvent
import com.example.spirit.R
import com.example.spirit.base.BaseActivity
import com.example.spirit.page.menu.view.MenuFragment

class MainActivity : BaseActivity() {

    private val TAG:String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentTransaction=supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.activity_main,MenuFragment(),"PlaylistFragment")
        fragmentTransaction.addToBackStack("PlaylistFragment")
        fragmentTransaction.commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 获取当前回退栈中的Fragment个数
            val backStackEntryCount = fragmentManager.backStackEntryCount
            // 回退栈中至少有多个fragment,栈底部是首页
            if (backStackEntryCount > 1) {
                // 立即回退一步
                fragmentManager.popBackStackImmediate()
            } else {
                //回退栈中只剩一个时,退出应用
                finish()
            }
        }
        return true
    }

}