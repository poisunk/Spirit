package com.example.spirit.page.toning.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class ToningBarBehavior(
    private val context: Context,
    private val attr: AttributeSet?,
)
    : CoordinatorLayout.Behavior<View>(context,attr) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return true
    }

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        child: View,
        layoutDirection: Int
    ): Boolean {
        var mStatusBarHeight:Int = 0
        val resourceId = parent.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId != 0) {
            mStatusBarHeight = parent.resources.getDimensionPixelSize(resourceId)
        }
        child.layout(0, mStatusBarHeight, 0, 0)
        return true
    }
}