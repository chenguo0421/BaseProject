package com.cg.base.utils

import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager

/**
 * @ProjectName:    siwencat
 * @CreateDate:     2021/2/6 11:28
 * @Author:         ChenGuo
 * @Description:     java类作用描述
 * @Version:        1.0
 */
object DeviceUtils {

    fun getScreenWidth(context: Context): Int {
        val displayMetrics = DisplayMetrics()
        context.display?.getRealMetrics(displayMetrics)
        return displayMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        val displayMetrics = DisplayMetrics()
        context.display?.getRealMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    fun getRealScreenWidth(context: Context): Int {
        val outSize = Point()
        context.display?.getRealSize(outSize)
        return outSize.x
    }

    fun getRealScreenHeight(context: Context): Int {
        val outSize = Point()
        context.display?.getRealSize(outSize)
        return outSize.y
    }

    private fun getWindowManager(context: Context): WindowManager {
        return context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }
}