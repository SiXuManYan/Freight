package com.sugar.library.frames.network

import com.google.gson.JsonObject

object JsonUtil {

    fun isLast(jsonObject: JsonObject) = jsonObject.has("last") && jsonObject.get("last").asBoolean
            || jsonObject.has("isLast") && jsonObject.get("isLast").asBoolean

}