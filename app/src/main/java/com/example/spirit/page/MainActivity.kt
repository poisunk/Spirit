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
        fragmentTransaction.add(R.id.activity_main,MenuFragment(),"MenuFragment")
            .addToBackStack("MenuFragment")
            .commit()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //退回栈中fragment的数量
            val backStackEntryCount = supportFragmentManager.backStackEntryCount
            //如果数量大于1则退出fragment
            //小于1则直接finish
            if (backStackEntryCount > 1) {
                supportFragmentManager.popBackStackImmediate()
            } else {
                finish()
            }
        }
        return true
    }

}