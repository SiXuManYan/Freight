package com.fatcloud.account.storage.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.blankj.utilcode.util.Utils
import com.fatcloud.account.ui.app.CloudAccountApplication
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
            if (!isLogin) {
                clearAll()
                return false
            }
            return true
        }

        fun getToken(): String {
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
            instance = null
        }

        fun getDeviceId(): String {
            return CommonUtils.getShareStudent().getString(Constants.SP_PUSH_DEVICE_ID, "")
        }

    }

    @PrimaryKey
    var id: Long = 0


    /**
     * 用户名，手机号
     */
    @ColumnInfo(name = "user_name")
    var username = ""


    @ColumnInfo(name = "token")
    var token = ""


    /**
     * 用户中文昵称
     */
    @ColumnInfo(name = "name")
    var name = ""

    /**
     * 用户英文昵称
     */
    @ColumnInfo(name = "enName")
    var enName = ""

    /**
     * 学生基础
     * S0-无基础
     * S1-有点基础
     * S2-小学一年级水平
     * S3-小学二年级水平
     * S4-小学三年级水平
     * S5-小学四年级水平
     * S6-小学五年级水平
     * S7-小学六年级水平
     */
    @ColumnInfo(name = "stage")
    var stage = ""

    /**
     * S7
     */
    @ColumnInfo(name = "stage_value")
    var stageValue = ""

    /**
     * 小学六年级水平
     */
    @ColumnInfo(name = "stage_text")
    var stageText = ""

    /**
     * M-男
     * F-女
     */
    @ColumnInfo(name = "sex")
    var sex = ""

    /**
     * 头像
     */
    @ColumnInfo(name = "head_image")
    var headImg = ""

    /**
     * 生日
     */
    @ColumnInfo(name = "birthday")
    var birthday = ""

    /**
     * STUDENT-学生
     */
    @ColumnInfo(name = "type")
    var type = ""

    /**
     * STUDENT
     */
    @ColumnInfo(name = "type_value")
    var typeValue = ""

    /**
     * 学生
     */
    @ColumnInfo(name = "type_text")
    var typeText = ""


}