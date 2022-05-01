package com.example.spirit.page.toning.model

import com.example.spirit.bean.ColorListBean

/**
 *创建者： poisunk
 *邮箱：1714480752@qq.com
 */
object ToningDataUtil {


    fun getColorListMessage(colorList:  List<ColorListBean.Color>) : List<ColorListBean.Color> {
        for(color: ColorListBean.Color in colorList){
            color.mes = ToningDataUtil.getColorMessage(color)
        }

        return colorList
    }

    fun getColorMessage(color: ColorListBean.Color):String{
        val builder = StringBuffer()
        builder.append("HEX #" + color.hex + "\n")
            .append("RGB " + color.r + "," + color.g + "," + color.b + "\n")
            .append("CMYK " + color.c + "," + color.m + "," + color.y + "," + color.k + "\n")

        return builder.toString()
    }

}

