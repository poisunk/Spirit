package com.example.spirit.page.toning.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.spirit.network.Repository

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class ToningDetailViewModel:ViewModel(){

    private val getColorDetailLiveData = MutableLiveData<Int>()

    val colorDetailLiveData = Transformations
        .switchMap(getColorDetailLiveData){
            Repository.getColorDetail(it)
        }

    fun getColorDetail(id:Int){
        getColorDetailLiveData.value = id
    }
}