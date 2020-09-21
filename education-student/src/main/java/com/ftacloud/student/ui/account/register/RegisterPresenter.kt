package com.ftacloud.student.ui.account.register

import com.blankj.utilcode.util.StringUtils
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.network.response.BasePresenter
import com.sugar.library.util.ProductUtils
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/21 0021 9:34.
 * </br>
 *
 */
class RegisterPresenter @Inject constructor(private var view: RegisterView) : BasePresenter(view) {

    fun register(phoneValue: String, verifyValue: String, passwordValue: String, passwordAgainValue: String) {
        if (!ProductUtils.isPhoneNumber(phoneValue)) {
            return
        }

        if (!ProductUtils.checkEmpty(verifyValue, StringUtils.getString(R.string.login_verify_empty))) {
            return
        }

        if (!ProductUtils.checkEmpty(passwordValue, StringUtils.getString(R.string.login_password_empty))) {
            return
        }

        if (passwordAgainValue.isEmpty()) {
            ToastUtils.showShort(R.string.password_input_again_hint)
            return
        }

        if (passwordValue != passwordAgainValue){
            ToastUtils.showShort(R.string.password_different_hint)
            return
        }

        view.registerSuccess()



    }


}