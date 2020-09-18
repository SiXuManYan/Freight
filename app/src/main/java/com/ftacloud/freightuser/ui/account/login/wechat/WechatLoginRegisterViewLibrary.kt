package com.ftacloud.freightuser.ui.account.login.wechat

import com.sugar.library.frames.network.response.LibraryBaseTaskView


/**
 * Created by Wangsw on 2020/6/18 0018 20:42.
 * </br>
 *
 */
interface WechatLoginRegisterViewLibrary : LibraryBaseTaskView {
    /**
     * 账户是否存在
     * @param existed 用户是否存在
     */
    fun accountExistedTag(existed: Boolean, account: String)
}