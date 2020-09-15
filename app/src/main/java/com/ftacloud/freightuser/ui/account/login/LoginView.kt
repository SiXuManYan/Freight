package com.ftacloud.freightuser.ui.account.login

import com.fatcloud.account.entity.wechat.WechatAuthInfo
import com.sugar.library.frames.network.response.BaseTaskView

/**
 * Created by Wangsw on 2020/6/1 0001 17:10.
 * </br>
 *
 */
interface LoginView : BaseTaskView {

    /**
     * 账户是否存在
     * @param existed 用户是否存在
     */
    fun accountExistedTag(existed: Boolean, account: String)

    /**
     * AccessTokenSuccess 获取成功
     */
    fun getWechatAccessTokenSuccess( wechatAuthInfo: WechatAuthInfo?)

    /**
     * 微信登录成功
     */
    fun wechatLoginSuccess()
}