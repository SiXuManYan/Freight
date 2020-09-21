package com.ftacloud.student.ui.account.bind

import android.view.View
import butterknife.OnClick
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.MainActivity
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.util.Constants
import com.sugar.library.util.ProductUtils
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Created by Wangsw on 2020/9/21 0021 17:09.
 * </br>
 *
 */
class BindPhoneActivity : BaseMVPActivity<BindPhonePresenter>(), BindPhoneView {

    override fun getLayoutId() = R.layout.activity_bind_phone


    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun initViews() {



    }


    @OnClick(
        R.id.verify_code_tv,
        R.id.bind_login_tv
    )
    fun onClick(view: View) {
        ProductUtils.handleDoubleClick(view)
        when (view.id) {

            R.id.verify_code_tv -> {
                ToastUtils.showShort("获取验证码")
            }

            R.id.bind_login_tv -> {
                val phoneValue = phone_aet.text.toString().trim()
                val verifyValue = verify_code_aet.text.toString().trim()
                val passwordValue = password_aet.text.toString().trim()
                val passwordAgainValue = password_again_aet.text.toString().trim()
                presenter.bindLogin(phoneValue, verifyValue, passwordValue, passwordAgainValue)

            }
        }
    }


    override fun loginSuccess() {
        startActivityClearTop(MainActivity::class.java, null)
        RxBus.post(Event(Constants.EVENT_NEED_REFRESH))
        finish()
    }


}