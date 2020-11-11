package com.fatcloud.account.ui.order.list.child

import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.StringUtils
import com.bumptech.glide.Glide
import com.fatcloud.account.R
import com.fatcloud.account.frames.entity.Order
import com.sugar.library.frames.BaseItemViewHolder
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_order.*

/**
 * 代金券列表项
 * @author dengxh
 * @date 2018/11/18
 */
class OrderChildHolder(parent: ViewGroup?) : BaseItemViewHolder<Order>(parent, R.layout.item_order), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: Order?) {
        if (data == null) {
            return
        }

        order_id_tv.text = StringUtils.getString(R.string.order_id_format, data.id)
        Glide.with(context).load(data.productIconImg).into(image_iv)
        order_name_tv.text = data.productName

        order_status_tv.text = data.state.substring(data.state.indexOf("-") + 1)

        if (data.state.contains(Order.OrderState.WAIT_PAY.name)) {
            pay_tv.visibility = View.VISIBLE
        } else {
            pay_tv.visibility = View.GONE
        }


    }


}