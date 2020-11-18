package com.fatcloud.account.ui.message

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import com.blankj.utilcode.util.ToastUtils
import com.fatcloud.account.BuildConfig
import com.fatcloud.account.frames.entity.Message
import com.fatcloud.account.frames.entity.request.ListRequest
import com.fatcloud.account.frames.network.response.BasePresenter
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.sugar.library.frames.network.subscriber.BaseHttpSubscriber
import com.sugar.library.frames.network.subscriber.BaseJsonArrayHttpSubscriber
import java.util.ArrayList
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/25 0025 14:00.
 * </br>
 *
 */
class MessagePresenter @Inject constructor(private var view: MessageView) : BasePresenter(view) {

    override fun loadList(lifecycle: LifecycleOwner, page: Int, pageSize: Int, lastItemId: String?) {

        val apply = ListRequest().apply {
            lastId = lastItemId
            size = pageSize
        }

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,
            apiService.getMessageList(apply), object : BaseJsonArrayHttpSubscriber<Message>(view) {

                override fun onSuccess(jsonArray: JsonArray?, list: ArrayList<Message>, lastItemId: String?) {

                    if (BuildConfig.DEBUG) {
                        val apply1 = Message().apply {
                            id = "1"
                            title = "Sugar"
                            content = "cutest girl"
                            state = "READ-已读"
                            state = "READ-已读"
                            type = "SYSTEM-系统消息"
                        }
                        list.add(apply1)
                        list.add(apply1)
                        list.add(apply1)
                        list.add(apply1)
                        list.add(apply1)
                        list.add(apply1)
                        list.add(apply1)
                        list.add(apply1)
                        list.add(apply1)
                        list.add(apply1)
                    }
                    view.bindList(list, lastItemId)
                }

            }
        )

    }


    fun setRead(lifecycle: LifecycleOwner) {

        requestApi(lifecycle, Lifecycle.Event.ON_DESTROY,
            apiService.setRead(), object : BaseHttpSubscriber<JsonObject>(view) {
                override fun onSuccess(data: JsonObject?) = Unit
            }
        )

    }


}