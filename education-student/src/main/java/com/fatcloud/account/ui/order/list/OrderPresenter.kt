package com.fatcloud.account.ui.order.list

import com.fatcloud.account.frames.network.response.BasePresenter
import javax.inject.Inject

class OrderPresenter @Inject constructor(private var orderView: OrderView): BasePresenter(orderView) {

}