package com.ftacloud.student.ui.order.list.child

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.StringUtils
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.Voucher
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.ui.view.countdown.CountDownTextView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_class_experience.*
import kotlinx.android.synthetic.main.item_order.*

/**
 * 代金券列表项
 * @author dengxh
 * @date 2018/11/18
 */
class VoucherViewHolder(parent: ViewGroup?) : BaseItemViewHolder<Voucher>(parent, R.layout.item_order), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: Voucher?) {
        if (data == null) {
            return
        }

        order_id_tv.text = StringUtils.getString(R.string.order_id_format, data.id)
        Glide.with(context).load(data.productIconImg).into(image_iv)
        order_name_tv.text = data.productName

        order_status_tv.text = data.state.substring(data.state.indexOf("-") + 1)

        if (data.state.contains(Voucher.OrderState.WAIT_PAY.name)) {
            pay_tv.visibility = View.VISIBLE
            expired_time_tv.visibility = View.VISIBLE

            if (data.countDownPaySeconds > 0) {
                initCountDown(data.countDownPaySeconds)
            }

        } else {
            pay_tv.visibility = View.GONE
            expired_time_tv.visibility = View.GONE
        }


    }


    private fun initCountDown(countDownPaySeconds: Long) {
        val endTime = countDownPaySeconds
        if (endTime <= 0) {
            return
        }

        val millisInFuture: Long = endTime - System.currentTimeMillis()
        if (millisInFuture <= 0) {
            countdown_tv.visibility = View.GONE
        } else {
            countdown_tv.apply {
                visibility = View.VISIBLE
                cancel()
                setTimeInFuture(SystemClock.elapsedRealtime() + millisInFuture)
                setAutoDisplayText(true)
                setTimeFormat(CountDownTextView.TIME_SHOW_D_H_M_S);
                setModifierText(context.getString(R.string.expiration_time));
                start()
                addCountDownCallback(object : CountDownTextView.CountDownCallback {

                    override fun onTick(countDownTextView: CountDownTextView?, millisUntilFinished: Long) = Unit
                    override fun onFinish(countDownTextView: CountDownTextView?) {
                        expired_time_tv.visibility = View.GONE
                    }
                })
            }
        }


    }

}