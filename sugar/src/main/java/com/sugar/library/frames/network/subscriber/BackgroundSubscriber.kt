package com.sugar.library.frames.network.subscriber

import com.sugar.library.frames.network.response.BaseView

abstract class BackgroundSubscriber<T>(private var view: BaseView) : BaseHttpSubscriber<T>(view) {




    override fun onError(e: Throwable) {

    }

    override fun onStart() {
        request(java.lang.Long.MAX_VALUE)
    }
}