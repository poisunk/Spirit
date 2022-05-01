package com.example.spirit.page.toning.viewmodel

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.example.spirit.bean.ColorPageBean
import com.example.spirit.network.Repository
import com.example.spirit.page.toning.model.ToningDataUtil

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
class ToningViewModel : ViewModel() {

    private val getColorPageLiveData= MutableLiveData<Boolean>()

    val colorPages = ArrayList<ColorPageBean.list>()

    val fragments = ArrayList<Fragment>()

    val colorPageLiveData = Transformations
        .switchMap(getColorPageLiveData){
            Repository.getColorPage()
        }

    fun getColorPage(){
        getColorPageLiveData.value = true
    }
}