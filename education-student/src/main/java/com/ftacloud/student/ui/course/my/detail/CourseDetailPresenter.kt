package com.ftacloud.student.ui.course.my.detail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.frames.entity.CourseDetail
import com.ftacloud.student.frames.entity.request.CourseDetailRequest
import com.ftacloud.student.frames.entity.request.SetUserInfo
import com.ftacloud.student.frames.network.response.BasePresenter
import com.google.gson.JsonElement
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/23 0023 9:56.
 * </br>
 *
 */
class CourseDetailPresenter @Inject constructor(private var view: CourseDetailView) : BasePresenter(view) {


    /**
     * 获取验证码
     */
    fun setUserInfo(lifecycle: LifecycleOwner, id: String) {


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