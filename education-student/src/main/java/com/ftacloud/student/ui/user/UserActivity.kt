package com.ftacloud.student.ui.user

import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity

/**
 * Created by Wangsw on 2020/10/8 0008 16:27.
 * </br>
 *
 */
class UserActivity : BaseMVPActivity<UserPresenter>(), UserView {

    override fun getLayoutId() = R.layout.activity_user

    override fun initViews() {

    }

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

}