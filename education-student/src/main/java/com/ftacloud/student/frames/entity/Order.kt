package com.ftacloud.student.frames.entity

import androidx.room.FtsOptions.Order


/**
 * Created by Wangsw on 2020/10/19 0019 14:06.
 * </br>
 *
 */
class Order {

    enum class OrderState {
        WAIT_PAY,
        UNPAID,
        PAYING,
        PAID,
        EDN
    }

/*
    {
        "id": "1308376450772111403",
        "no": "668",
        "productIconImg": "http://thirdwx.qlogo.cn/mmopen/vi_32/8rgx7V7WuRKPMGeTtrQEDEpdYITcLzdKRt2FxQ3OURMqHLTXlibbVP2d58o220qK5nCkG6bAvib1gsaicAiaUoDsYg/132",
        "productName": "产品编号一",
        "scheduleQuantity": 10,
        "state": "WAIT_PAY-待支付"
    }
*/

    var id = ""
    var no = ""
    var productIconImg = ""
    var productName = ""
    var scheduleQuantity = ""

    /**
     * WAIT_PAY-待支付
     * UNPAID-未支付
     * PAYING-支付中
     * PAID-已支付
     * EDN-订单结束
     */
    var state = ""

    /**
     * 订单数量
     */
    var productQuantity = 0

    /**
     * 订单倒计时
     */
    var countDownPaySeconds: Long = 0L

    /**
     * 创建时间
     */
    var createAt = ""


}