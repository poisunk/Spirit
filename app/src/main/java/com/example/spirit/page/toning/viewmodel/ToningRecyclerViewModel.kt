package com.example.spirit.page.toning.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.spirit.bean.ColorListBean
import com.example.spirit.network.Repository

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class ToningRecyclerViewModel : ViewModel() {

    private val getColorListLiveData = MutableLiveData<Int>()

    val colorList = ArrayList<ColorListBean.Color>()

    val colorListLiveData = Transformations
        .switchMap(getColorListLiveData){
            Repository.getColorList(it)
        }

    fun getColorList(id:Int){
        getColorListLiveData.value = id
    }
}