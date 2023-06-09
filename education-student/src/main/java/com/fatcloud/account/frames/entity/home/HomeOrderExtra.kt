package com.fatcloud.account.frames.entity.home

import java.io.Serializable

/**
 * Created by Wangsw on 2020/10/22 0022 19:20.
 * </br>
 * 订单
 */
class HomeOrderExtra : Serializable {

    var orderId = ""

    var productId = ""

    var productName = ""

    var productIconImg = ""

    var productIntroduce = ""

    var productMoney = ""

    var payingMoney = ""

    var quantity: Int = 0
}