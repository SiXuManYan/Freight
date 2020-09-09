package com.sugar.library.net

/**
 * 接口回调
 *
 */
class Response<T> constructor() {

    constructor(code: Int?) : this() {
        this.code = code
    }


    /**
     * result code
     */
    var code: Int? = 0

    /** 错误信息 */
    var msg: String? = ""


    /** 接口数据 */
    var data: T? = null

    /**
     * 是否Api接口返回了错误
     */
    fun isApiError(): Boolean {
        return code != 200
    }
}