package com.ftacloud.freightuser.ui.account.password

import com.sugar.library.frames.network.response.LibraryBaseTaskView


/**
 * Created by Wangsw on 2020/6/2 0002 15:54.
 * </br>
 *
 */
interface PasswordSetViewLibrary : LibraryBaseTaskView {

    /**
     * 注册成功
     */
    fun registerSuccess()

    /**
     * 密码重设成功
     */
    fun passwordResetSuccess()
}