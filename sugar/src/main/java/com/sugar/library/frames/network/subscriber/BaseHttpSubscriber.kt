package com.sugar.library.frames.network.subscriber

import com.blankj.utilcode.util.StringUtils
import com.google.gson.Gson
import com.sugar.library.R
import com.sugar.library.frames.network.LibraryApiException
import com.sugar.library.frames.network.Response
import com.sugar.library.frames.network.response.BaseTaskView
import com.sugar.library.frames.network.response.BaseView
import io.reactivex.subscribers.ResourceSubscriber
import retrofit2.HttpException
import java.io.IOException

/**
 * 网络请求任务订阅基类
 */
abstract class BaseHttpSubscriber<T>(private var view: BaseView, var showLoading: Boolean = true) : ResourceSubscriber<Response<T>>() {

    protected val gson by lazy { Gson() }

    override fun onStart() {
        super.onStart()
        if (view is BaseTaskView && showLoading) {
            (view as BaseTaskView).showLoading()
        }
    }

    override fun onComplete() {
        if (view is BaseTaskView && showLoading) {
            (view as BaseTaskView).hideLoading()
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
                view.showError(e.code(), e.message())
            }
            is IOException -> {
                view.showError(-1, StringUtils.getString(R.string.time_out))
            }
            is LibraryApiException -> {
                view.showError(e.code, e.msg ?: StringUtils.getString(R.string.resonse_error))
            }
            else -> {
                view.showError(-1, StringUtils.getString(R.string.service_error))
            }
        }
    }

    /**
     * 处理回调
     * @param data 数据
     */
    abstract fun onSuccess(data: T?)
}