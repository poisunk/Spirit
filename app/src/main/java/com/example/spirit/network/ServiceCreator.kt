package com.example.spirit.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object ServiceCreator {

    private const val BASE_URL="http://redrock.udday.cn:8888"

    private val retrofit= Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass:Class<T>):T= retrofit.create(serviceClass)
}