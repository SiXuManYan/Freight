package com.ftacloud.student.ui.home.holder

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.ftacloud.student.R
import com.ftacloud.student.frames.entity.home.Course
import com.ftacloud.student.frames.entity.home.CourseState
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.frames.glides.RoundTransFormation
import com.sugar.library.ui.view.countdown.CountDownTextView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_course_common.*

/**
 * Created by Wangsw on 2020/10/13 0013 11:17.
 * </br>
 *  普通课程 (只有蓝色)
 */
class CommonClassHolder(parent: ViewGroup?) : BaseItemViewHolder<Course>(parent, R.layout.item_home_course_common), LayoutContainer {

    override val containerView: View? get() = itemView


    override fun setData(data: Course?) {
        if (data == null) {
            return
        }
        Glide.with(context).load(data.courseIconImg)
            .apply(RequestOptions().transform(MultiTransformation(CenterCrop(), RoundTransFormation(context, 8)))).into(image_iv)
        title_tv.text = data.productName

        content_tv.text = data.teacherName
        Glide.with(context).load(data.teacherHeadImg).into(teacher_civ)

        when {
            data.state.contains(CourseState.UNTEACH.name) -> {
                // 未上课，显示倒计时
                course_vs.displayedChild = 1
                initCountDown(data)
            }
            data.state.contains(CourseState.TAUGHT.name) -> {
                course_vs.displayedChild = 0
                status_tv.text = context.getString(R.string.class_end)
            }
            data.state.contains(CourseState.TEACHING.name) -> {
                course_vs.displayedChild = 0
                status_tv.text = context.getString(R.string.class_teaching)
            }
        }


    }

    private fun initCountDown(data: Course) {
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