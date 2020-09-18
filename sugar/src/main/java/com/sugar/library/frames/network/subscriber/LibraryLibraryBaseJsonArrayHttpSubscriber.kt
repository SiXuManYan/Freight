package com.sugar.library.frames.network.subscriber

import com.sugar.library.frames.network.response.LibraryBaseView
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.sugar.library.frames.network.Response
import java.lang.reflect.ParameterizedType
import java.util.*

/**
 * data json array
 * 不需要传key
 */
abstract class LibraryLibraryBaseJsonArrayHttpSubscriber<T>(viewLibrary: LibraryBaseView, showLoading: Boolean = true) :
    LibraryBaseHttpSubscriber<JsonArray>(viewLibrary, showLoading) {

    override fun onNext(response: Response<JsonArray>) {
        if (response.isApiError()) {
            return
        }
        val type = (this::class.java.genericSuperclass as ParameterizedType).actualTypeArguments[0]
        val jsonArray = response.data
        val list = ArrayList<T>()
        var lastItemId: String? = ""

        try {
            jsonArray?.let {


                it.forEachIndexed { index, element ->
                    list.add(gson.fromJson(element, type))

                    // 获取最后一项item id ,用于请求下一页
                    if (index == it.size() - 1) {
                        if (element is JsonObject && element.has("id")) {
                            lastItemId = element.get("id").asString
                        }
                    }

                }

            }

        } catch (e: Exception) {
            onError(e)
            return
        }

        onSuccess(jsonArray, list, lastItemId)

    }

    override fun onSuccess(data: JsonArray?) {

    }

    abstract fun onSuccess(jsonArray: JsonArray?, list: ArrayList<T>, lastItemId: String?)


}