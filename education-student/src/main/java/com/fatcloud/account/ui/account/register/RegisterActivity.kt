package com.fatcloud.account.ui.account.register

import android.view.View
import butterknife.OnClick
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils
import com.fatcloud.account.R
import com.fatcloud.account.common.StudentUtil
import com.fatcloud.account.frames.components.BaseMVPActivity
import com.fatcloud.account.ui.main.MainActivity
import com.sugar.library.util.CommonUtils
import com.sugar.library.util.ProductUtils
import kotlinx.android.synthetic.main.activity_register.*

/**
 * Created by Wangsw on 2020/9/21 0021 9:34.
 * </br>
 *
 */
class RegisterActivity : BaseMVPActivity<RegisterPresenter>(), RegisterView {

    /**
     * 是否为密文
     */
    private var isCipherText = true


    private var againIsCipherText = true

    override fun getLayoutId() = R.layout.activity_register

    override fun showLoading() = showLoadingDialog()

    override fun hideLoading() = dismissLoadingDialog()

    override fun initPadLayout() {
        CommonUtils.setStatusBarTransparent(this)
        BarUtils.setNavBarVisibility(this, false)
    }

    override fun initViews() {

    }


    private fun changeDisplayMethod() {

        if (isCipherText) {
            // 切换至明文
            StudentUtil.editOpen(password_aet, password_rule_iv)
        } else {
            // 切换至密文
            StudentUtil.editDismiss(password_aet, password_rule_iv)
        }
        isCipherText = !isCipherText
    }

    private fun changeAgainDisplayMethod() {

        if (againIsCipherText) {
            // 切换至明文
            StudentUtil.editOpen(password_again_aet, password_again_rule_iv)
        } else {
            // 切换至密文
            StudentUtil.editDismiss(password_again_aet, password_again_rule_iv)
        }
        againIsCipherText = !againIsCipherText
    }


    @OnClick(
        R.id.back_login_tv,
        R.id.get_verify_tv,
        R.id.register_tv,
        R.id.password_rule_iv,
        R.id.password_again_rule_iv
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

                if (!register_protocol.isChecked) {
                    ToastUtils.showShort(getString(R.string.please_agree_agreement))
                    return
                }

                val phoneValue = phone_aet.text.toString().trim()
                val verifyValue = verify_code_aet.text.toString().trim()
                val passwordValue = password_aet.text.toString().trim()
                val passwordAgainValue = password_again_aet.text.toString().trim()
                presenter.register(this, phoneValue, verifyValue, passwordValue, passwordAgainValue)
            }
            R.id.password_rule_iv -> {
                changeDisplayMethod()
            }
            R.id.password_again_rule_iv -> {
                changeAgainDisplayMethod()
            }


        }
    }

    override fun registerSuccess() {
        startActivityClearTop(MainActivity::class.java, null)
        finish()
    }

    override fun captchaSendResult() {
        ToastUtils.showShort(R.string.captcha_target_format)
    }

}