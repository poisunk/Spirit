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

            val backStackEntryCount = supportFragmentManager.backStackEntryCount

            if (backStackEntryCount > 1) {
                supportFragmentManager.popBackStackImmediate()
            } else {
                finish()
            }
        }
        return true
    }

}