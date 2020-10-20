package com.ftacloud.student.ui.order.detail

import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity

/**
 * Created by Wangsw on 2020/10/20 0020 14:36.
 * </br>
 *  订单详情
 */
class OrderDetailActivity : BaseMVPActivity<OrderDetailPresenter>(), OrderDetailView {

    override fun getLayoutId() = R.layout.activity_order_detail

    override fun initViews() {
        setMainTitle(getString(R.string.order_detail))
    }









}