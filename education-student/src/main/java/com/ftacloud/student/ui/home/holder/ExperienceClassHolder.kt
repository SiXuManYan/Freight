package com.ftacloud.student.ui.home.holder

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.StringUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.home.Schedule
import com.ftacloud.student.frames.entity.home.ScheduleState
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.ui.view.countdown.CountDownTextView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_class_experience.*

/**
 * Created by Wangsw on 2020/10/13 0013 11:16.
 * </br>
 *  体验课
 *  根据状态区分倒计时
 */
class ExperienceClassHolder(parent: ViewGroup?) : BaseItemViewHolder<Schedule>(parent, R.layout.item_home_class_experience), LayoutContainer {

    override val containerView: View? get() = itemView

    override fun setData(data: Schedule?) {
        if (data == null) {
            return
        }
        title_tv.text = data.courseName
        content_tv.text = data.courseIntroduce


        if (data.state == ScheduleState.UNACTIVE.name) {
            // 未预约体验课
            content_switcher.displayedChild = 0
            card_cv.setCardBackgroundColor(ColorUtils.getColor(R.color.color_f9a74d))

            type_container.removeAllViews()
            data.productTags.forEach {
                val textView = ViewGroup.inflate(context, R.layout.item_home_class_experience_tag, null) as TextView
                textView.text = it
                type_container.addView(textView)
            }

            money_tv.text = StringUtils.getString(com.sugar.library.R.string.money_symbol_format, data.productMoney)
            val oldMoney = StringUtils.getString(com.sugar.library.R.string.money_symbol_format, data.productMoneyOfDiscount)
            SpanUtils.with(old_money_tv).appendLine(oldMoney).setStrikethrough().create()

        } else {
            // 已预约体验课
            card_cv.setCardBackgroundColor(ColorUtils.getColor(R.color.color_5cbebc))
            content_switcher.displayedChild = 1

            when (data.state) {

                ScheduleState.UNTEACH.name -> {

                    if (data.countDownToStudyTimeSeconds > 0) {
                        count_down_ll.visibility = View.VISIBLE
                        initCountDown(data)

                    } else {
                        count_down_ll.visibility = View.GONE
                    }

                }
                ScheduleState.TAUGHT.name -> {
                    product_state_tv.text = "已结束"
                    count_down_ll.visibility = View.GONE
                }

                ScheduleState.TEACHING.name -> {
                    product_state_tv.text = "上课中"
                    count_down_ll.visibility = View.GONE
                }

            }

        }


    }

    private fun initCountDown(data: Schedule) {
        val endTime = data.countDownToStudyTimeSeconds
        if (endTime <= 0) {
            return
        }

        // 订单创建时间一小时倒计时
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
                start()
                addCountDownCallback(object : CountDownTextView.CountDownCallback {

                    override fun onTick(countDownTextView: CountDownTextView?, millisUntilFinished: Long) = Unit

                    override fun onFinish(countDownTextView: CountDownTextView?) {
                        product_state_tv.text = "上课中"
                        count_down_ll.visibility = View.GONE
                        // RxBus.post(Event(Constants.EVENT_REFRESH_ORDER_LIST_FROM_END_COUNT_DOWN))
                    }
                })
            }
        }


    }

}