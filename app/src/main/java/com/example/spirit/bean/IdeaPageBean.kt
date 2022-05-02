package com.example.spirit.bean

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
data class IdeaPageBean(val code:Int,
                        val message:String,
                        val data:List<Data>){

    data class Data(val id:Int,
                    val name:String,
                    val image:String)

}
