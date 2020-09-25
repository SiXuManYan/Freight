package com.ftacloud.student.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blankj.utilcode.util.Utils
import com.ftacloud.student.ui.app.CloudAccountApplication
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants

/**
 * 用户信息
 * @date 2018/11/11
 */
@Entity(tableName = "tb_user")
class User {

    companion object {
        private var instance: User? = null
            get() {
                if (field == null) {
                    field = (Utils.getApp() as CloudAccountApplication).database.userDao().findUser()
                    if (field == null) {
                        field = User()
                    }
                }
                return field
            }

        @Synchronized
        fun get() = instance!!

        fun isLogon(): Boolean {
            val isLogin = CommonUtils.getShareStudent().getBoolean(Constants.SP_LOGIN)
            if (isLogin && get().id == 0L) {
                clearAll()
                return false
            }
            return isLogin
        }

        fun getToken():String{
            if (isLogon()) {
                return CommonUtils.getShareStudent().getString(Constants.SP_TOKEN, "")
            }
            return ""
        }

        fun update() {
            instance = null
        }

        fun clearAll() {
            (Utils.getApp() as CloudAccountApplication).database.userDao().clear()
            CommonUtils.getShareStudent().remove(Constants.SP_LOGIN)
            CommonUtils.getShareStudent().remove(Constants.SP_NOVICE)
            instance = null
        }
    }

    @PrimaryKey
    var id: Long = 0

/*
    {
        "code": "200",
        "msg": "成功",
        "data": {
        "username": "17640339671",
        "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdWJqZWN0IiwiaXNzIjoiU3VuU2hhbmdxaWFuZyIsIm5hbWUiOiIiLCJpZCI6IjEzMDkyOTYyNzA3MTU3ODUyMTYiLCJleHAiOjE2MzIwOTk1MzEsInR5cGUiOiJTVFVERU5UIiwidXNlcm5hbWUiOiIxNzY0MDMzOTY3MSJ9.t4Q21O9v-RdJv6kTVgfQHDi-4VpT_gjxfnPZ08QUjJc"
    }
    }*/



    /**
     * 用户名，手机号
     */
    @ColumnInfo(name = "user_name")
    var username = ""


    @ColumnInfo(name = "token")
    var token = ""



}