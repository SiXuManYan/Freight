package com.fatcloud.account.ui.course.my.child

import android.os.SystemClock
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.fatcloud.account.R
import com.fatcloud.account.common.OssUtil
import com.fatcloud.account.frames.entity.MyCourse
import com.fatcloud.account.frames.entity.home.CourseState
import com.fatcloud.account.ui.app.CloudAccountApplication
import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.frames.BaseItemViewHolder
import com.sugar.library.frames.glides.RoundTransFormation
import com.sugar.library.ui.widget.countdown.CountDownTextView
import com.sugar.library.util.Constants
import com.sugar.library.util.TimeUtil
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_home_course_common.*

/**
 * Created by Wangsw on 2020/9/27 0027 10:07.
 * </br>
 *  我的课程  --> 课程详情
 */
class MyCourseChildHolder(parent: ViewGroup?) : BaseItemViewHolder<MyCourse>(parent, R.layout.item_home_course_common), LayoutContainer {

    override val containerView: View? get() = itemView

    private var checkTime = false

    override fun setData(data: MyCourse?) {
        if (data == null) {
            return
        }

        OssUtil.getRealOssUrl(context, data.productIconImg, object : CloudAccountApplication.OssSignCallBack {
            override fun ossUrlSignEnd(url: String) {
                Glide.with(context).load(url)
                    .apply(RequestOptions().transform(MultiTransformation(CenterCrop(), RoundTransFormation(context, 8))))
                    .into(image_iv)
            }
        })

        OssUtil.getRealOssUrl(context, data.teacherHeadImg, object : CloudAccountApplication.OssSignCallBack {
            override fun ossUrlSignEnd(url: String) {
                Glide.with(context).load(url).into(teacher_civ)
            }
        })


        title_tv.text = data.productName
        content_tv.text = data.teacherName

        when {
            data.state.contains(CourseState.UNTEACH.name) -> {
                // 未上课，显示倒计时
                val endTime = TimeUtil.getSafeCountDownTime(data.countDownStudySeconds) *1000
                if (endTime - System.currentTimeMillis() > 1000 * 60 * 60 * 24) {
                    // 大于一天显示开课时间
                    course_vs.displayedChild = 1
                    enter_ll.visibility = View.GONE
                    countdown_tv.text = data.studyDatetime
                } else {
                    // 一天以内显示倒计时
                    initCountDown(endTime)
                    course_vs.displayedChild = 0
                    enter_ll.visibility = View.VISIBLE
                }
            }
            data.state.contains(CourseState.TAUGHT.name) -> {
                // 已结束
                course_vs.displayedChild = 0
                status_tv.text = context.getString(R.string.class_end)
                enter_ll.visibility = View.GONE
            }
            data.state.contains(CourseState.TEACHING.name) -> {
                // 上课中
                course_vs.displayedChild = 0
                status_tv.text = context.getString(R.string.class_teaching)
                enter_ll.visibility = View.VISIBLE
            }
        }
    }

    private fun initCountDown(endTime: Long) {
        // 一天以外显示开课时间 ，一天以内倒计时，12分钟时刷新接口，15 分钟内显示进入教室(正在上课)，
        if (endTime <= 0) {
            // 老师没有点，做容错处理，学生可以进入教室
            countdown_tv.visibility = View.VISIBLE
            status_tv.text = context.getString(R.string.having_class_now)
            return
        }

        countdown_tv.apply {
            visibility = View.VISIBLE
            cancel()
            setTimeInFuture(SystemClock.elapsedRealtime() + endTime)
            setAutoDisplayText(true)
            setTimeFormat(CountDownTextView.TIME_SHOW_D_H_M_S);
            start()
            addCountDownCallback(object : CountDownTextView.CountDownCallback {

                override fun onTick(countDownTextView: CountDownTextView?, millisUntilFinished: Long) {

                    when {

                        millisUntilFinished <= 1000 * 60 * 12 -> {
                            // 刷新列表，获取上课码
                            if (!checkTime) {
                                checkTime = true
                                RxBus.post(Event(Constants.EVENT_REFRESH_MY_COURSE))
                            }
                        }

                        millisUntilFinished <= 1000 * 60 * 10 -> {
                            status_tv.text = context.getString(R.string.having_class_now)
                            countdown_tv.visibility = View.GONE
                        }
                    }
                }

                override fun onFinish(countDownTextView: CountDownTextView?) {
                    status_tv.text = context.getString(R.string.having_class_now)
                    course_vs.displayedChild = 0
                    RxBus.post(Event(Constants.EVENT_REFRESH_MY_COURSE))
                }
            })
        }


    }


}