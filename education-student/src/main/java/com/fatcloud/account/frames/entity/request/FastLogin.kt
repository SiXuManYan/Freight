package com.fatcloud.account.frames.entity.request

import com.fatcloud.account.common.StudentConstants
import com.fatcloud.account.storage.entity.User

/**
 * Created by Wangsw on 2020/9/23 0023 14:41.
 * </br>
 *
 */
class FastLogin {
    var username: String = ""
    var vc: String = ""

    val type: String = StudentConstants.TYPE_STUDENT
    val clientType: String = StudentConstants.CLIENT_TYPE_PHONE
    val osType: String = StudentConstants.OS_TYPE
    val osVersion: String = StudentConstants.OS_VERSION
    val pushDeviceId: String = User.getDeviceId()
}