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
            val data = Network.getColorPage()
            Result.success(data)
        }catch (e: Exception){
            Result.failure(e)
        }
        emit(result)
    }

    fun getColorList(id:Int) = liveData(Dispatchers.IO) {
        val result = try{
            val data = Network.getColorList(id)
            Result.success(data)
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }

    fun getColorDetail(id:Int) = liveData(Dispatchers.IO) {
        val result = try{
            val data = Network.getColorDetail(id)
            Result.success(data)
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }

    fun userRegister(phoneNumber:String, name:String) = liveData(Dispatchers.IO) {
        val result = try{
            val data = Network.userRegister(phoneNumber,name)
            Result.success(data)
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }

    fun userLogin(phoneNumber:String) = liveData(Dispatchers.IO) {
        val result = try{
            val data = Network.userLogin(phoneNumber)
            Result.success(data)
        }catch (e:Exception){
            Result.failure(e)
        }
        emit(result)
    }

}