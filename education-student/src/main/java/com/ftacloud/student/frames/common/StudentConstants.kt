package com.ftacloud.student.frames.common

import android.os.Build
import com.blankj.utilcode.util.DeviceUtils


/**
 * Created by Wangsw on 2020/9/25 0025 9:14.
 * </br>
 *
 */
object StudentConstants {

    /**
     * app 类型
     * 学生端:STUDENT
     */
    const val TYPE_STUDENT = "STUDENT"

    /**
     * 系统类型
     */
    const val OS_TYPE = "ANDROID"

    /**
     * 系统 OS 版本号
     */
    val OS_VERSION = "Android " + Build.VERSION.RELEASE

    /**
     * 设备类型(平板 / 手机)
     */
    var CLIENT_TYPE_PHONE = if (DeviceUtils.isTablet()) {
        "PAD"
    } else {
        "PHONE"
    }


}