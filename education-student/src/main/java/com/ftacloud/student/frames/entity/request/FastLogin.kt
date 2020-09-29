package com.ftacloud.student.frames.entity.request

import com.ftacloud.student.frames.common.StudentConstants

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


}