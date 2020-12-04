package com.fatcloud.account.ui.home.holder

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SpanUtils
import com.blankj.utilcode.util.StringUtils
import com.fatcloud.account.R
import com.fatcloud.account.frames.entity.home.Course
import com.fatcloud.account.frames.entity.home.CourseState
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.ui.widget.countdown.CountDownTextView
import com.sugar.library.util.TimeUtil
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_class_experience.*

/**
 * Created by Wangsw on 2020/10/13 0013 11:16.
 * </br>
 *  体验课
 *  根据状态区分倒计时
 */
class ExperienceClassHolder(parent: ViewGroup?) : BaseItemViewHolder<Course>(parent, R.layout.item_home_class_experience), LayoutContainer {

    override val containerView: View? get() = itemView

    override fun setData(data: Course?) {
        if (data == null) {
            return
        }
        title_tv.text = data.productName
        content_tv.text = data.productIntroduce


        if (data.stateValue.contains(CourseState.UNACTIVE.name)) {
            // 未预约体验课
            content_switcher.displayedChild = 0
            card_cv.setCardBackgroundColor(ColorUtils.getColor(R.color.color_ff7a18))

            type_container.removeAllViews()
            data.productTags.forEach {
                val textView = ViewGroup.inflate(context, R.layout.item_home_class_experience_tag, null) as TextView
                textView.text = it
                type_container.addView(textView)
            }

            money_tv.text = StringUtils.getString(com.sugar.library.R.string.money_symbol_format, data.productMoneyOfDiscount)
            val oldMoney = StringUtils.getString(com.sugar.library.R.string.money_symbol_format, data.productMoney)
            SpanUtils.with(old_money_tv).appendLine(oldMoney).setStrikethrough().create()

        } else {
            // 已预约体验课
            card_cv.setCardBackgroundColor(ColorUtils.getColor(R.color.color_5cbebc))
            content_switcher.displayedChild = 1

            when (data.stateValue) {

                CourseState.UNTEACH.name -> {
                    val safeCountDownTime = TimeUtil.getSafeCountDownTime(data.countDownToStudyTimeSeconds)
                    initCountDown(safeCountDownTime)
                }
                CourseState.TAUGHT.name -> {
                    product_state_tv.text = "已结束"
                    count_down_ll.visibility = View.GONE
                }

                CourseState.TEACHING.name -> {
                    product_state_tv.text = "上课中"
                    count_down_ll.visibility = View.GONE
                }

            }

        }


    }

    private fun initCountDown(endTime: Long) {

        if (endTime <= 0) {
            countdown_tv.visibility = View.GONE
        } else {
            count_down_ll.visibility = View.VISIBLE
            countdown_tv.apply {
                visibility = View.VISIBLE
                cancel()
                setTimeInFuture(SystemClock.elapsedRealtime() + endTime * 1000)
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