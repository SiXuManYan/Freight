package com.ftacloud.student.ui.account.login

import com.blankj.utilcode.util.StringUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.network.response.BasePresenter
import com.sugar.library.util.ProductUtils
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/21 0021 9:33.
 * </br>
 *
 */
class LoginPresenter @Inject constructor(private var view: LoginView) : BasePresenter(view) {


    /**
     * @param verifyChecked true 验证码登录
     *                      false 密码登录
     * @param phoneValue 手机号
     * @param verifyValue 验证码
     * @param passwordValue 密码
     */
    fun handleLogin(verifyChecked: Boolean, phoneValue: String, verifyValue: String, passwordValue: String) {
        if (!ProductUtils.isPhoneNumber(phoneValue)) {
            return
        }
        if (verifyChecked) {

            if (!ProductUtils.checkEmpty(verifyValue, StringUtils.getString(R.string.login_verify_empty))) {
                return
            }
            verifyLogin(phoneValue, verifyValue)
        } else {

            if (!ProductUtils.checkEmpty(passwordValue, StringUtils.getString(R.string.login_password_empty))) {
                return
            }
            passwordLogin(phoneValue, passwordValue)
        }

    }


    /**
     * 验证码登录
     */
    private fun verifyLogin(phoneValue: String, verifyValue: String) {

view.loginSuccess()
    }

    /**
     * 密码登录
     */
    private fun passwordLogin(phoneValue: String, verifyValue: String) {
        view.loginSuccess()
    }


}