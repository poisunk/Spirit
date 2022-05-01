package com.example.spirit.network

import com.example.spirit.bean.ColorListBean
import com.example.spirit.bean.ColorPageBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
interface NetworkService {

    @GET("/color/page")
    fun getColorPage(): Call<ColorPageBean>

    @GET("/color/color_list")
    fun getColorList(@Query("theme_id") id:Int): Call<ColorListBean>
}