package com.sugar.library.frames.network.response

/**
 * 视图基类
 */
interface BaseView {

    /**
     * 提示错误
     * @param code     错误码
     * @param message  错误信息
     */
    fun showError(code: Int, message: String)

}