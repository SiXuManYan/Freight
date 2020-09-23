package com.ftacloud.student.frames.network.request

import android.os.Build

/**
 * Created by Wangsw on 2020/9/23 0023 14:41.
 * </br>
 *
 */
class FastLogin {
    var username: String = ""
    var vc: String = ""

    val type: String = "STUDENT"
    val clientType: String = "PHONE"
    val osType: String = "ANDROID"
    val osVersion: String = Build.VERSION.RELEASE + "_" + Build.VERSION.SDK_INT


}