package com.ftacloud.student.ui.account

import android.view.View
import com.blankj.utilcode.util.BarUtils
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.ui.account.login.LoginActivity
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.Constants
import io.reactivex.functions.Consumer

/**
 * Created by Wangsw on 2020/9/18 0018 15:15.
 * </br>
 *
 */
class WelcomeActivity : BaseMVPActivity<WelcomePresenter>(), WelcomeView {


    override fun getLayoutId() = R.layout.activity_welcome

    override fun initViews() {
        CommonUtils.setStatusBarTransparent(this)
        BarUtils.setNavBarVisibility(this, false)
        initEvent()
    }

    private fun initEvent() {
        presenter.subsribeEvent(Consumer {
            when (it.code) {
                Constants.EVENT_NEED_REFRESH -> {
                    finish()
                }
            }
        })
    }

    fun loginOrRegister(view: View) {
        startActivityClearTop(LoginActivity::class.java, null)
    }

    fun wechatLogin(view: View) {

    }


}