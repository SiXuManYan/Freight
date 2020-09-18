package com.ftacloud.student.ui.account

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ScreenUtils
import com.ftacloud.student.MainActivity
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.sugar.library.util.CommonUtils
import kotlinx.android.synthetic.main.activity_welcome.*

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



    }

    fun loginOrRegister(view: View) {
        startActivity(MainActivity::class.java)
    }
    fun wechatLogin(view: View) {
        startActivity(MainActivity::class.java)
    }


}