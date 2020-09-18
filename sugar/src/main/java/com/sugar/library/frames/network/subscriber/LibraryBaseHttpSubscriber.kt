package com.sugar.library.frames.network.subscriber

import com.sugar.library.frames.network.LibraryApiException
import com.sugar.library.frames.network.response.LibraryBaseTaskView
import com.sugar.library.frames.network.response.LibraryBaseView
import com.google.gson.Gson
import com.sugar.library.frames.network.Response
import io.reactivex.subscribers.ResourceSubscriber
import retrofit2.HttpException
import java.io.IOException

/**
 * 网络请求任务订阅基类
 */
abstract class LibraryBaseHttpSubscriber<T>(private var viewLibrary: LibraryBaseView, var showLoading: Boolean = true) : ResourceSubscriber<Response<T>>() {

    protected val gson by lazy { Gson() }

    override fun onStart() {
        super.onStart()
        if (viewLibrary is LibraryBaseTaskView && showLoading) {
            (viewLibrary as LibraryBaseTaskView).showLoading()
        }
    }

    override fun onComplete() {
        if (viewLibrary is LibraryBaseTaskView && showLoading) {
            (viewLibrary as LibraryBaseTaskView).hideLoading()
        }
    }

    override fun onNext(response: Response<T>) {
        onSuccess(response.data)
//        onComplete()
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        onComplete()
        when (e) {
            is HttpException -> {
                viewLibrary.showError(e.code(), e.message())
            }
            is IOException -> {
                viewLibrary.showError(-1, "网络连接超时")
            }
            is LibraryApiException -> {
                viewLibrary.showError(e.code, e.msg ?: "接口异常")
            }
            else -> {
                viewLibrary.showError(-1, "服务异常")
            }
        }
    }

    /**
     * 处理回调
     * @param data 数据
     */
    abstract fun onSuccess(data: T?)
}