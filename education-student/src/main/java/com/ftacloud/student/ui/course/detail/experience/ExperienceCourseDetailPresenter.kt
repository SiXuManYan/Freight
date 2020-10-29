package com.ftacloud.student.ui.course.detail.experience

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.entity.CourseDetail
import com.ftacloud.student.frames.entity.request.CourseDetailRequest
import com.ftacloud.student.frames.network.response.BasePresenter
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/23 0023 9:56.
 * </br>
 *
 */
class ExperienceCourseDetailPresenter @Inject constructor(private var view: ExperienceCourseDetailView) : BasePresenter(view) {


    /**
     * 获取验证码
     */
    fun getCourseDetail(lifecycle: LifecycleOwner, id: String) {

        val apply = CourseDetailRequest().apply {
            scheduleId = id
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            apiService.getCourseDetail(apply), object : BaseHttpSubscriber<CourseDetail>(view) {

                override fun onSuccess(data: CourseDetail?) {
                    data?.let {

                        view.bindData(it)

                    }
                }

                override fun onError(e: Throwable) {

                }

            }
        )

    }

}