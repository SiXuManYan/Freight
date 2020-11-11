package com.fatcloud.account.frames.entity.request

/**
 * Created by Wangsw on 2020/9/23 0023 14:41.
 * </br>
 *
 */
class GetVerifyCode {
    var username :String =  ""

    val type: String = "STUDENT"
    val clientType: String = "PHONE"
    val osType: String = "ANDROID"
    val osVersion: String = android.os.Build.VERSION.RELEASE + "_" + android.os.Build.VERSION.SDK_INT


}