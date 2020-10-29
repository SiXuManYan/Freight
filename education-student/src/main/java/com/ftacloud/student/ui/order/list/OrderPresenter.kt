package com.ftacloud.student.ui.order.list

import com.ftacloud.student.frames.network.response.BasePresenter
import javax.inject.Inject

class OrderPresenter @Inject constructor(private var orderView: OrderView): BasePresenter(orderView) {

}