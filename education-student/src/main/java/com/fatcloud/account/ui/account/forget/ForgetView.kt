package com.fatcloud.account.ui.account.forget

import com.sugar.library.frames.network.response.BaseTaskView

/**
 * Created by Wangsw on 2020/9/21 0021 9:37.
 * </br>
 *
 */
interface ForgetView : BaseTaskView {
    fun retrieveSuccess()
    fun captchaSendResult()
}