package com.example.spirit.page.toning.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.marginBottom

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class ToningViewPagerBehavior(private val context: Context,
                              private val attr: AttributeSet?)
    : CoordinatorLayout.Behavior<View>(context,attr) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return dependency is ConstraintLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        val h = dependency.measuredHeight + dependency.marginBottom
        child.y = dependency.y + h.toFloat()
        child.layoutParams.height = child.measuredHeight - parent.y.toInt()
        return true
    }
}