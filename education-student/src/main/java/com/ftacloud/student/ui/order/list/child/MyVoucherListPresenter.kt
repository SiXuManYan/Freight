package com.ftacloud.student.ui.order.list.child

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.frames.entity.Voucher
import com.ftacloud.student.frames.entity.request.ListRequest
import com.ftacloud.student.frames.network.response.BasePresenter
import com.ftacloud.student.ui.order.list.MyVoucherActivity.Companion.TYPE_ORDER_ALL
import com.ftacloud.student.ui.order.list.MyVoucherActivity.Companion.TYPE_ORDER_PAID
import com.ftacloud.student.ui.order.list.MyVoucherActivity.Companion.TYPE_ORDER_PAYING
import com.ftacloud.student.ui.order.list.MyVoucherActivity.Companion.TYPE_ORDER_UNPAID
import com.google.gson.JsonArray
import com.sugar.library.frames.network.subscriber.BaseJsonArrayHttpSubscriber
import java.util.*
import javax.inject.Inject

class MyVoucherListPresenter @Inject constructor(private var view: MyVoucherListView) : BasePresenter(view) {


    fun loadOrderList(lifecycle: LifecycleOwner, pageSize: Int, lastItemId: String?, categoryValue: Int?) {
        val apply = ListRequest().apply {
            lastId = lastItemId
            size = pageSize
        }


        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            when (categoryValue) {

                TYPE_ORDER_UNPAID -> {
                    apiService.getOrderListUnpaid(apply)
                }
                TYPE_ORDER_PAYING -> {
                    apiService.getOrderList(apply)
                }
                TYPE_ORDER_PAID -> {
                    apiService.getOrderListPaid(apply)
                }
                else -> {
                    apiService.getOrderList(apply)
                }
            },


            object : BaseJsonArrayHttpSubscriber<Voucher>(view) {

                override fun onSuccess(jsonArray: JsonArray?, list: ArrayList<Voucher>, lastItemId: String?) {
                    view.bindList(list, lastItemId)
                }

                override fun onError(e: Throwable) {
                    ToastUtils.showShort("列表请求失败")
                }

            }
        )

    }

}