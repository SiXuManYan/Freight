package com.fatcloud.account.ui.course.detail.prepare

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fatcloud.account.frames.entity.FormalCourseDetail
import com.fatcloud.account.frames.entity.request.FormalCourseDetailRequest
import com.fatcloud.account.frames.network.response.BasePresenter
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
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