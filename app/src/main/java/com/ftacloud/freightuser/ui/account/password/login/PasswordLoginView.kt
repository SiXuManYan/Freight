package com.ftacloud.freightuser.ui.account.password.login

import com.sugar.library.frames.network.response.BaseTaskView


/**
 * Created by Wangsw on 2020/6/2 0002 18:08.
 * </br>
 *
 */
interface PasswordLoginView : BaseTaskView {
    /**
     * 登录成功
     */
    fun loginSuccess()
}