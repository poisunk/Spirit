package com.example.spirit.bean

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
data class IdeaListBean(val code:Int,
                        val message:String,
                        val data:Data){

    data class Data(val ideaDetail:Int)
}