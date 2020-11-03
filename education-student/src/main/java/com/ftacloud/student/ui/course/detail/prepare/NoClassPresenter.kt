package com.ftacloud.student.ui.course.detail.prepare

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.entity.CourseDetail
import com.ftacloud.student.frames.entity.FormalCourseDetail
import com.ftacloud.student.frames.entity.Order
import com.ftacloud.student.frames.entity.request.FormalCourseDetailRequest
import com.ftacloud.student.frames.entity.request.ListRequest
import com.ftacloud.student.frames.network.response.BasePresenter
import com.google.gson.JsonArray
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import com.sugar.library.frames.network.subscriber.BaseJsonArrayHttpSubscriber
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/29 0029 11:15.
 * </br>
 *
 */
class NoClassPresenter @Inject constructor(private var view: NoClassView) : BasePresenter(view) {

    fun loadDetail(lifecycleOwner: LifecycleOwner, id: String) {

        val apply = FormalCourseDetailRequest().apply {
            productId = id
        }

        requestApi(lifecycleOwner, Lifecycle.Event.ON_DESTROY,

            apiService.getFormalCourseDetail(apply), object : BaseHttpSubscriber<FormalCourseDetail>(view) {
                override fun onSuccess(data: FormalCourseDetail?) {
                    if (data == null) {
                        return
                    }
                    view.bindDetail(data)
                }
            })

    }

}