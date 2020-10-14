package com.ftacloud.student.ui.home.holder

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import com.blankj.utilcode.util.ColorUtils
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.home.Schedule
import com.ftacloud.student.frames.entity.home.ScheduleState
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.ui.view.countdown.CountDownTextView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_class_experience.*
import kotlinx.android.synthetic.main.item_home_course_order0.*
import kotlinx.android.synthetic.main.item_home_course_order0.card_cv
import kotlinx.android.synthetic.main.item_home_course_order0.content_tv
import kotlinx.android.synthetic.main.item_home_course_order0.countdown_tv
import kotlinx.android.synthetic.main.item_home_course_order0.money_tv
import kotlinx.android.synthetic.main.item_home_course_order0.title_tv

/**
 * Created by Wangsw on 2020/10/13 0013 11:17.
 * </br>
 *  订单课
 */
class OrderClassHolder (parent: ViewGroup?) : BaseItemViewHolder<Schedule>(parent, R.layout.item_home_course_order0), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: Schedule?) {
        if (data == null) {
            return
        }
        Glide.with(context).load(data.courseIconImg).into(image_iv)
        title_tv.text = data.courseName

        if (data.state ==  ScheduleState.UNACTIVE.name) {
            // 未激活
            card_cv.setCardBackgroundColor(ColorUtils.getColor(R.color.color_f9a74d))
            content_tv.text = data.courseIntroduce
            teacher_civ.visibility = View.GONE
            state_vs.displayedChild = 0
            money_tv.text = data.productMoney
        }else{
            // 已购买
            card_cv.setCardBackgroundColor(ColorUtils.getColor(R.color.color_5cbebc))
            content_tv.text = data.teacherName
            teacher_civ.visibility = View.VISIBLE
            Glide.with(context).load(data.teacherHeadImg).into(teacher_civ)

            state_vs.displayedChild = 1
            when (data.state) {
                ScheduleState.UNTEACH.name -> {
                    // 未上课，显示倒计时
                    course_vs.displayedChild = 1
                    initCountDown(data)

                }
                ScheduleState.TAUGHT.name->{
                    course_vs.displayedChild = 0
                    status_tv.text = "已结束"
                }
                 ScheduleState.TEACHING.name->{
                    course_vs.displayedChild = 0
                    status_tv.text = "上课中"
                }
                else -> {
                }
            }



        }
    }

    private fun initCountDown(data: Schedule) {
        val endTime = data.countDownToStudyTimeSeconds
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
                start()
                addCountDownCallback(object : CountDownTextView.CountDownCallback {

                    override fun onTick(countDownTextView: CountDownTextView?, millisUntilFinished: Long) = Unit

                    override fun onFinish(countDownTextView: CountDownTextView?) {
                        status_tv.text = "上课中"
                        course_vs.displayedChild = 0
                        // RxBus.post(Event(Constants.EVENT_REFRESH_ORDER_LIST_FROM_END_COUNT_DOWN))
                    }
                })
            }
        }


    }


}