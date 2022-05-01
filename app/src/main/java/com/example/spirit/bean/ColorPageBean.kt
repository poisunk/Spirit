package com.example.spirit.bean

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
data class ColorPageBean(val code:Int,
                     val message:String,
                     val data:Data){

    data class Data(val count:Int,
                    val list:List<list>)

    data class list(val id:Int,
                    val theme:String)
}


