package com.fatcloud.account.ui.course.detail.experience

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fatcloud.account.frames.entity.CourseDetail
import com.fatcloud.account.frames.entity.request.CourseDetailRequest
import com.fatcloud.account.frames.entity.request.OrderCourseDetailRequest
import com.fatcloud.account.frames.network.response.BasePresenter
import com.google.gson.JsonObject
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/23 0023 9:56.
 * </br>
 *
 */
class ExperienceCourseDetailPresenter @Inject constructor(private var view: ExperienceCourseDetailView) : BasePresenter(view) {


    /**
     * 获取体验课详情
     */
    fun getDetail(lifecycle: LifecycleOwner, id: String) {

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
            })

    }


    /**
     * 获取订单课详情
     */
    fun getOrderCourseDetail(lifecycle: LifecycleOwner, orderId: String,productId: String) {

        val apply = OrderCourseDetailRequest().apply {
            this.orderId = orderId
            this.productId = productId
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            apiService.getOrderDetail(apply), object : BaseHttpSubscriber<CourseDetail>(view) {

                override fun onSuccess(data: CourseDetail?) {
                    data?.let {
                        view.bindData(it)

                    }
                }
            })

    }


    /**
     * 预约体验课
     */
    fun bookingExperience(lifecycle: LifecycleOwner, id: String) {

        val apply = CourseDetailRequest().apply {
            scheduleId = id
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            apiService.bookingExperience(apply), object : BaseHttpSubscriber<JsonObject>(view) {

                override fun onSuccess(data: JsonObject?) {
                    view.bookingExperienceSuccess()
                }
            })

    }

}