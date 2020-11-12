package com.fatcloud.account.ui.task

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fatcloud.account.frames.entity.Task
import com.fatcloud.account.frames.entity.request.ListRequest
import com.fatcloud.account.frames.network.response.BasePresenter
import com.google.gson.JsonArray
import com.sugar.library.frames.network.subscriber.BaseJsonArrayHttpSubscriber
import java.util.ArrayList
import javax.inject.Inject


/**
 * Created by Wangsw on 2020/9/27 0027 14:23.
 * </br>
 *
 */
class TaskPresenter @Inject constructor(private var view: TaskView) : BasePresenter(view) {


    override fun loadList(lifecycle: LifecycleOwner, page: Int, pageSize: Int, lastItemId: String?) {
        val apply = ListRequest().apply {
            lastId = lastItemId
            size = pageSize
        }


        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            apiService.getTaskList(apply),


            object : BaseJsonArrayHttpSubscriber<Task>(view) {

                override fun onSuccess(jsonArray: JsonArray?, list: ArrayList<Task>, lastItemId: String?) {
                    view.bindList(list, lastItemId)
                }

            }
        )
    }

}