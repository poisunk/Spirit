package com.example.spirit.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object Network {
    private val networkService=ServiceCreator.create(NetworkService::class.java)

    suspend fun getColorPage() = networkService.getColorPage().await()

    suspend fun getColorList(id:Int) = networkService.getColorList(id).await()

    suspend fun getColorDetail(id:Int) = networkService.getColorDetail(id).await()

    suspend fun getIdeaPage() = networkService.getIdeaPage().await()

    suspend fun userRegister(phoneNumber:String, name:String) = networkService.userRegister(phoneNumber, name).await()

    suspend fun userLogin(phoneNumber:String) = networkService.userLogin(phoneNumber).await()

    private suspend fun <T> Call<T>.await():T{

        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if(body!=null){
                        continuation.resume(body)
                    }else{
                        continuation.resumeWithException(RuntimeException("response body is null"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }

    }
}