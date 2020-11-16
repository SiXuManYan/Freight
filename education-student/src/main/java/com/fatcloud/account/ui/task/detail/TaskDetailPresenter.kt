package com.fatcloud.account.ui.task.detail

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fatcloud.account.frames.entity.TaskDetail
import com.fatcloud.account.frames.entity.request.TaskDetailRequest
import com.fatcloud.account.frames.network.response.BasePresenter

import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/27 0027 13:43.
 * </br>
 *
 */
class TaskDetailPresenter @Inject constructor(private var view: TaskDetailView) : BasePresenter(view) {


    fun loadDetail(lifecycle: LifecycleOwner, courseId: String?) {
        val apply = TaskDetailRequest().apply {
            productId = courseId
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