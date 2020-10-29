package com.ftacloud.student.ui.course.my.child

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.ftacloud.student.frames.entity.MyCourse
import com.ftacloud.student.frames.entity.Order
import com.ftacloud.student.frames.entity.request.ListRequest
import com.ftacloud.student.frames.network.response.BasePresenter
import com.ftacloud.student.ui.course.my.MyCourseActivity.Companion.COURSE_UN_TEACH
import com.ftacloud.student.ui.order.list.OrderActivity
import com.google.gson.JsonArray
import com.sugar.library.frames.network.subscriber.BaseJsonArrayHttpSubscriber
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/27 0027 10:03.
 * </br>
 *
 */
class MyCourseChildPresenter @Inject constructor(private var view: MyCourseChildView) : BasePresenter(view) {

    fun loadCourseList(lifecycle: LifecycleOwner, pageSize: Int, lastItemId: String?, categoryValue: Int?) {
        val apply = ListRequest().apply {
            lastId = lastItemId
            size = pageSize
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            when (categoryValue) {

                COURSE_UN_TEACH -> {
                    apiService.unTeachCourse(apply)
                }
                else -> {
                    apiService.listTaught(apply)
                }
            },

            object : BaseJsonArrayHttpSubscriber<MyCourse>(view) {

                override fun onSuccess(jsonArray: JsonArray?, list: ArrayList<MyCourse>, lastItemId: String?) {
                    view.bindList(list, lastItemId)
                }
            }
        )

    }

}