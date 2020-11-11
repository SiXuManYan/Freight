package com.fatcloud.account.ui.account.bind

import com.fatcloud.account.frames.network.response.BasePresenter
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/21 0021 17:09.
 * </br>
 *
 */
class BindPhonePresenter @Inject constructor(private var view: BindPhoneView) : BasePresenter(view) {


    fun bindLogin(phoneValue: String, verifyValue: String, passwordValue: String, passwordAgainValue: String) {
        view.loginSuccess()
    }
}