package com.example.spirit.bean

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
data class ColorListBean(val code:Int,
                         val message:String,
                         val data:Data){

    data class Data(val has_more:Boolean, val color_list:List<Color>)

    data class Color(val id:Int,
                     val name:String,
                     val hex:String,
                     val r:Int,
                     val g:Int,
                     val b:Int,
                     val c:Int,
                     val m:Int,
                     val k:Int,
                     val y:Int,
                     var mes:String)

}
