package com.example.spirit.network

import com.example.spirit.bean.*
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST
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

    @GET("/color/color_detail")
    fun getColorDetail(@Query("color_detail_id") id:Int): Call<ColorDetailBean>

    @GET("/idea/idea")
    fun getIdeaPage(): Call<IdeaPageBean>

    @POST("/user/register")
    fun userRegister(@Field("phone_number") phoneNumber:String, @Field("name") name:String): Call<RegisterBean>

    @POST("/user/login")
    fun userLogin(@Field("phone_number") phoneNumber:String): Call<LoginBean>
}