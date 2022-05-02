package com.example.spirit.bean

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
data class IdeaDetailBean(val code:Int, val message:String, val data:Data){

    data class Data(val title:String,
                    val image:String,
                    val intro:String,
                    val colors:ColorDetailBean.Data.Colors,
                    val shades: ColorDetailBean.Data.Shades)
}
