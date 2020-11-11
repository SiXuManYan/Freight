package com.sugar.library.event

/**
 * Created by Wangsw on 2020/6/16 0016 18:56.
 * </br>
 *
 */
class WechatPayResultEvent(
    /** / 0 支付成功
    -2 用户取消支付
   -1 支付错误 (-1) */
    val resultCode: Int)