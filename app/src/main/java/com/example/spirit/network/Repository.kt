package com.example.spirit.network

import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object Repository {

    fun getColorPage() = liveData(Dispatchers.IO){
        val result=try {
            val colorPageBean = Network.getColorPage()
            Result.success(colorPageBean)
        }catch (e: Exception){
            Result.failure(e)
        }
        emit(result)
    }

    fun getColorList(id:Int) = liveData(Dispatchers.IO) {
        val result = try{
            val colorListBean = Network.getColorList(id)
            Result.success(colorListBean)
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }

    fun getColorDetail(id:Int) = liveData(Dispatchers.IO) {
        val result = try{
            val colorDetailBean = Network.getColorDetail(id)
            Result.success(colorDetailBean)
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }
}