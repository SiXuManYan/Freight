package com.ftacloud.student.ui.account.retrieve

import com.ftacloud.student.frames.network.response.BasePresenter
import javax.inject.Inject

/**
 * Created by Wangsw on 2020/9/21 0021 9:37.
 * </br>
 *
 */
class RetrievePresenter  @Inject constructor(private var view: RetrieveView) : BasePresenter(view)  {
    fun retrieve(phoneValue: String, verifyValue: String, passwordValue: String, passwordAgainValue: String) {
        view.retrieveSuccess()
    }

}