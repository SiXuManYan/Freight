package com.ftacloud.student.ui.order.pay

import com.ftacloud.student.frames.entity.defray.WechatPayInfo
import com.sugar.library.frames.network.response.BaseTaskView


/**
 * Created by Wangsw on 2020/6/16 0016 16:02.
 * </br>
 *
 */
interface PayView : BaseTaskView {

    /**
     * 进行微信支付
     */
    fun doWechatPay(it: WechatPayInfo)

    /**
     * 进行支付宝支付
     */
    fun doAliPay(it: String)

    /**
     * 订单支付成功
     */
    fun orderPaySuccess()

    /**
     * 后台未查询到支付结果，提示用户
     */
    fun checkOrderRealPaymentStatusFailure()


}