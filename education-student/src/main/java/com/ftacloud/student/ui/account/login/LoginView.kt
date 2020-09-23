package com.ftacloud.student.ui.account.login

import com.sugar.library.frames.network.response.BaseTaskView

/**
 * Created by Wangsw on 2020/9/21 0021 9:33.
 * </br>
 *
 */
interface LoginView : BaseTaskView {

    fun loginSuccess()


    fun captchaSendResult()
}