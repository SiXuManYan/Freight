package com.fatcloud.account.storage

import androidx.room.TypeConverter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.math.BigDecimal


object Converters {

    @TypeConverter
    @JvmStatic
    fun bigDecimalToText(value: BigDecimal?): String {
        value?.let {
            return value.toPlainString()
        }
        return ""
    }

    @TypeConverter
    @JvmStatic
    fun textToBigDecimal(value: String): BigDecimal {
        return if (value.isEmpty()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(value)
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromString(value: String?): ArrayList<String?>? {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    @JvmStatic
    fun fromArrayList(list: ArrayList<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }


}