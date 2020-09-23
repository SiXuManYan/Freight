package com.ftacloud.student.ui.account.register

import android.view.View
import butterknife.OnClick
import com.blankj.utilcode.util.ToastUtils
import com.ftacloud.student.MainActivity
import com.ftacloud.student.R
import com.ftacloud.student.frames.components.BaseMVPActivity
import com.ftacloud.student.ui.account.retrieve.RetrieveActivity
import com.sugar.library.event.Event
import com.sugar.library.event.RxBus
import com.sugar.library.util.Constants
import com.sugar.library.util.ProductUtils
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.get_verify_tv
import kotlinx.android.synthetic.main.activity_register.password_aet
import kotlinx.android.synthetic.main.activity_register.phone_aet
import kotlinx.android.synthetic.main.activity_register.verify_code_aet

/**
 * Created by Wangsw on 2020/9/21 0021 9:34.
 * </br>
 *
 */
class RegisterActivity : BaseMVPActivity<RegisterPresenter>(), RegisterView {

    override fun getLayoutId() = R.layout.activity_register


    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun initViews() {



    }


    @OnClick(
        R.id.back_login_tv,
        R.id.get_verify_tv,
        R.id.register_tv
    )
    fun onClick(view: View) {
        ProductUtils.handleDoubleClick(view)
        when (view.id) {
            R.id.back_login_tv -> {
                finish()
            }
            R.id.get_verify_tv -> {
                val phoneValue = phone_aet.text.toString().trim()
                presenter.getVerifyCode(this, phoneValue, get_verify_tv)
            }

            R.id.register_tv -> {
                val phoneValue = phone_aet.text.toString().trim()
                val verifyValue = verify_code_aet.text.toString().trim()
                val passwordValue = password_aet.text.toString().trim()
                val passwordAgainValue = password_again_aet.text.toString().trim()
                presenter.register(phoneValue, verifyValue, passwordValue, passwordAgainValue)

            }
        }
    }

    override fun registerSuccess() {
        startActivityClearTop(MainActivity::class.java, null)
        RxBus.post(Event(Constants.EVENT_NEED_REFRESH))
        finish()
    }

    override fun captchaSendResult() {
        ToastUtils.showShort(R.string.captcha_target_format)
    }

}