package com.example.spirit.bean

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
data class ColorDetailBean(val code:Int, val message:String, val data:Data){

    data class Data(val intro:String, val colors:Colors, val shades:Shades){

        data class Colors(val color_1:ColorListBean.Color,
                          val color_2:ColorListBean.Color,
                          val color_3:ColorListBean.Color,
                          val color_4:ColorListBean.Color,
                          val color_5:ColorListBean.Color,
                          val color_6:ColorListBean.Color,
                          val color_7:ColorListBean.Color)

        data class Shades(val shade_list:List<ShadeList>)

        data class ShadeList(val shade:List<Shade>)

        data class Shade(val color:ColorListBean.Color)

    }

}
