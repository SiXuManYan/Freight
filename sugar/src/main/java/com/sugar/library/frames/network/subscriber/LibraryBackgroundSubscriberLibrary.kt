package com.sugar.library.frames.network.subscriber

import com.sugar.library.frames.network.response.LibraryBaseView

abstract class LibraryBackgroundSubscriberLibrary<T>(private var viewLibrary: LibraryBaseView) : LibraryBaseHttpSubscriber<T>(viewLibrary) {




    override fun onError(e: Throwable) {

    }

    override fun onStart() {
        request(java.lang.Long.MAX_VALUE)
    }
}