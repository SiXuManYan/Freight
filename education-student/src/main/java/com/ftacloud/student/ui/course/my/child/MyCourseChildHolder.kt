package com.ftacloud.student.ui.course.my.child

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.MyCourse
import com.ftacloud.student.frames.entity.home.CourseState
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.ui.view.countdown.CountDownTextView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_course_common.*

/**
 * Created by Wangsw on 2020/9/27 0027 10:07.
 * </br>
 *  我的课程  --> 课程详情
 */
class MyCourseChildHolder (parent: ViewGroup?) : BaseItemViewHolder<MyCourse>(parent, R.layout.item_home_course_common), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: MyCourse?) {
        if (data == null) {
            return
        }

        Glide.with(context).load(data.productIconImg).into(image_iv)
        Glide.with(context).load(data.teacherHeadImg).into(teacher_civ)
        title_tv.text = data.productName
        content_tv.text = data.teacherName

        when  {
            data.state.contains(CourseState.UNTEACH.name) -> {
                // 未上课，显示倒计时
                course_vs.displayedChild = 1
                initCountDown(data)
            }
            data.state.contains(CourseState.TAUGHT.name)  -> {
                course_vs.displayedChild = 0
                status_tv.text = context.getString(R.string.class_end)
            }
            data.state.contains(CourseState.TEACHING.name) -> {
                course_vs.displayedChild = 0
                status_tv.text = context.getString(R.string.class_teaching)
            }
        }
    }


    private fun initCountDown(data: MyCourse) {
        val endTime = data.countDownStudySeconds
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