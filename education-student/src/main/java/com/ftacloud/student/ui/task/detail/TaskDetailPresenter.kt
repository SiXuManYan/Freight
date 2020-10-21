package com.ftacloud.student.ui.task.detail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.entity.Task
import com.ftacloud.student.frames.entity.TaskDetail
import com.ftacloud.student.frames.entity.request.ListRequest
import com.ftacloud.student.frames.entity.request.TaskDetailRequest
import com.ftacloud.student.frames.network.response.BasePresenter
import com.ftacloud.student.ui.task.detail.TaskDetailView
import com.google.gson.JsonArray
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import com.sugar.library.frames.network.subscriber.BaseJsonArrayHttpSubscriber
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/27 0027 13:43.
 * </br>
 *
 */
class TaskDetailPresenter @Inject constructor(private var view: TaskDetailView) : BasePresenter(view) {

    fun loadDetail(lifecycle: LifecycleOwner, courseId: String?) {
        val apply = TaskDetailRequest().apply {
            taskOfCourseId = courseId
        }


        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            apiService.getTaskDetail(apply),


            object : BaseHttpSubscriber<TaskDetail>(view) {
                override fun onSuccess(data: TaskDetail?) {
                    if (data == null) {
                        return
                    }
                    view.bindDetail(data)

                }

            }
        )
    }


}