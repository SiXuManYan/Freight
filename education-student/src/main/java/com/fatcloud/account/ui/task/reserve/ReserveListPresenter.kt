package com.fatcloud.account.ui.task.reserve

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.fatcloud.account.frames.entity.Buddy
import com.fatcloud.account.frames.entity.Task
import com.fatcloud.account.frames.entity.request.BuddyBookingRequest
import com.fatcloud.account.frames.entity.request.ListRequest
import com.fatcloud.account.frames.network.response.BasePresenter
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import com.sugar.library.frames.network.subscriber.BaseJsonArrayHttpSubscriber
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/11/2 0002 11:52.
 * </br>
 *
 */
class ReserveListPresenter @Inject constructor(private var view: ReserveListView) : BasePresenter(view) {


    override fun loadList(lifecycle: LifecycleOwner, page: Int, pageSize: Int, lastItemId: String?) {

        val apply = ListRequest().apply {
            lastId = lastItemId
            size = pageSize
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            apiService.buddyList(apply),

            object : BaseJsonArrayHttpSubscriber<Buddy>(view) {

                override fun onSuccess(jsonArray: JsonArray?, list: ArrayList<Buddy>, lastItemId: String?) {
                    view.bindList(list, lastItemId)
                }

            }
        )

    }


    /**
     * 预约陪读
     */
    fun book(lifecycle: LifecycleOwner, data: Buddy.BuddyTime, scheduleId: String) {

        val apply = BuddyBookingRequest().apply {
            this.buddyTimeId = data.buddyTimeId
            this.scheduleId = scheduleId
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,

            apiService.buddyBooking(apply),

            object : BaseHttpSubscriber<JsonObject>(view) {
                override fun onSuccess(data: JsonObject?) {
                  view.bookSuccess()
                }
            }
        )

    }


}